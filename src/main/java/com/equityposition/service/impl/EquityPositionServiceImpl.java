package com.equityposition.service.impl;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.equityposition.common.Constant;
import com.equityposition.common.ObjectUtil;
import com.equityposition.domain.dto.EquityPositionDto;
import com.equityposition.domain.mapper.EquityPositionMapper;
import com.equityposition.exception.ServiceException;
import com.equityposition.service.EquityPositionService;
import com.equityposition.service.models.EquityPosition;

@Service
public class EquityPositionServiceImpl implements EquityPositionService {
    Logger logger = LoggerFactory.getLogger(EquityPositionServiceImpl.class);
    @Autowired
    private EquityPositionMapper equityPositionMapper;

    /**
             * 判断是否已经执行了Cancel-action
     *
     * @param tradeId
     * @return
     */
    @Override
    public boolean isCanceled(Integer tradeId) {
        logger.info("isCanceled start ,tradeId=" + tradeId);
        boolean flag = true;
        EquityPositionDto ep = equityPositionMapper.getMaxVersion(tradeId);
        if (ep.getAction().intValue() != 2) {
            flag = false;
        }
        logger.info("isCanceled end flag=" + flag);
        return flag;
    }

    /**
             * 获取最新的一条记录
     *
     * @param tradeId
     * @return
     */
    @Override
    public EquityPosition getLatest(Integer tradeId) {
        logger.info("getLatest start,tradeId="+tradeId);
        EquityPositionDto ep=equityPositionMapper.getMaxVersion(tradeId);
        logger.info("getLatest end ep="+ JSON.toJSONString(ep));

        return ObjectUtil.map(ep,EquityPosition.class);
    }

    /**
            * 保存equity position
     *
     * @param equityPosition
     * @return
     */
    @Override
    public Integer insertOrUpdate(EquityPosition equityPosition) {
        EquityPositionDto equityPositionDto=ObjectUtil.map(equityPosition, EquityPositionDto.class);
        synchronized (equityPosition.getTradeID()){
        	EquityPositionDto ep=equityPositionMapper.getMaxVersion(equityPosition.getTradeID());
            if(ep==null){
                equityPositionDto.setAction(Constant.INSERT);
                equityPositionDto.setVersion(1);
            }else{
                if(isCanceled(equityPositionDto.getTradeID())){
                    throw new ServiceException("the equity position is canceled",400);
                }
                equityPositionDto.setAction(Constant.UPDATE);
                equityPositionDto.setVersion(ep.getVersion()+1);
            }
            if(equityPositionDto.getMode()==Constant.Sell){
                equityPositionDto.setQuantity(-equityPositionDto.getQuantity());
            }
            int flag=equityPositionMapper.insert(equityPositionDto);
            return flag;
        }
    }


    /**
     * @param equityPosition
     * @return
     */
    @Override
    public Integer cancel(EquityPosition equityPosition) {
        EquityPositionDto equityPositionDto=ObjectUtil.map(equityPosition, EquityPositionDto.class);
        synchronized (equityPosition.getTradeID()){
        	EquityPositionDto ep=equityPositionMapper.getMaxVersion(equityPosition.getTradeID());
            if(ep==null){
                throw new ServiceException("the tradeId isnot insert",400);
            }else if(isCanceled(equityPositionDto.getTradeID())){
                throw new ServiceException("the tradeId was canceled",400);
            }else{
                equityPositionDto.setVersion(ep.getVersion()+1);
                equityPositionDto.setAction(Constant.CANCEL);
            }
            int flag=equityPositionMapper.insert(equityPositionDto);
            return flag;
        }
    }

    /**
     * @return
     */
    @Override
    public HashMap<String, Integer> getOutput() {
    	List<EquityPositionDto> list=equityPositionMapper.getOutput();
        HashMap<String,Integer> map=new HashMap<>();
        map.put("REL",0);
        map.put("ITC",0);
        map.put("INF",0);
        for (EquityPositionDto ep:list) {
            if(ep.getAction()==Constant.CANCEL){
                continue;
            }

            if(ep.getSecurityCode()==Constant.REL){
                map.put("REL",map.get("REL")+ep.getQuantity());
            }else if(ep.getSecurityCode()==Constant.ITC){
                map.put("ITC",map.get("ITC")+ep.getQuantity());
            }else if(ep.getSecurityCode()== Constant.INF){
                map.put("INF",map.get("INF")+ep.getQuantity());
            }
        }

        return map;
    }
}
