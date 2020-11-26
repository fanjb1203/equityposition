package com.equityposition.common;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    public final static int REL=0;
    public final static int ITC=1;
    public final static int INF=2;

    public final static short Buy=0;
    public final static short Sell=1;

    public final static short INSERT=0;
    public final static short UPDATE=1;
    public final static short CANCEL=2;

    public static Map<Integer,Integer> PositionMap=new HashMap<>();

    static {
        PositionMap.put(0,60);
        PositionMap.put(1,0);
        PositionMap.put(2,50);
    }
}
