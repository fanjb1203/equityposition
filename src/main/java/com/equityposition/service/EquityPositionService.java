package com.equityposition.service;

import java.util.HashMap;

import com.equityposition.service.models.EquityPosition;

public interface EquityPositionService {
    /**
     * 判断是否已经执行了Cancel-action
     * @param tradeId
     * @return
     */
    boolean isCanceled(Integer tradeId);

    /**
     * 获取最新的一条记录
     * @param tradeId
     * @return
     */
    EquityPosition getLatest(Integer tradeId);

    /**
     * 保存equity position
     * @param equityPositionVO
     * @return
     */
    Integer insertOrUpdate(EquityPosition equityPositionVO);

    /**
     *
     * @param equityPositionVO
     * @return
     */
    Integer cancel(EquityPosition equityPositionVO);

    /**
     *
     * @return
     */
    HashMap<String,Integer> getOutput();
}