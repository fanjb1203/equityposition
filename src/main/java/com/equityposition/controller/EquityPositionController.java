package com.equityposition.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equityposition.common.RestResult;
import com.equityposition.common.ResultCode;
import com.equityposition.service.EquityPositionService;
import com.equityposition.service.models.EquityPosition;

@RestController
@RequestMapping("/position")
public class EquityPositionController {
	public final static Logger logger = LoggerFactory.getLogger(EquityPositionController.class);
    @Autowired
    private EquityPositionService service;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public RestResult<Boolean> save(@RequestBody EquityPosition equityPositionVO){
    	if(equityPositionVO.getTradeID() == null || equityPositionVO.getMode() == null ||
    			equityPositionVO.getSecurityCode() == null || equityPositionVO.getQuantity() == null) {
    		return RestResult.fail(ResultCode.PARAM_IS_INVALID);
    	}
    	try {
    		service.insertOrUpdate(equityPositionVO);
    		return RestResult.successNoData(ResultCode.SUCCESS);
    	}catch (Exception e) {
            logger.error(e.getMessage(), e);
            return RestResult.fail(ResultCode.DATA_IS_ERROR);
        }
    }
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public RestResult<Boolean> cancel(@RequestBody EquityPosition equityPositionVO){
    	if(equityPositionVO.getTradeID() == null || equityPositionVO.getMode() == null ||
    			equityPositionVO.getSecurityCode() == null || equityPositionVO.getQuantity() == null) {
    		return RestResult.fail(ResultCode.PARAM_IS_INVALID);
    	}
        try {
        	service.cancel(equityPositionVO);
    		return RestResult.successNoData(ResultCode.SUCCESS);
    	}catch (Exception e) {
            logger.error(e.getMessage(), e);
            return RestResult.fail(ResultCode.DATA_IS_ERROR);
        }
    }
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public RestResult<Map<String,Integer>> getResult(){
        try {
        	Map<String,Integer> map = service.getOutput();
    		return RestResult.success(map,ResultCode.SUCCESS);
    	}catch (Exception e) {
            logger.error(e.getMessage(), e);
            return RestResult.fail(ResultCode.DATA_IS_ERROR);
        }
    }
}
