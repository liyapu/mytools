package com.lyp.learn.dp.pattern.flyweight2;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ChesspieceFactory {
    private static Map<String,Chesspiece> pools = new HashMap<>();

    static {
        pools.put("黑",new ChesspieceFlyweight("黑"));
        pools.put("白",new ChesspieceFlyweight("白"));
    }

    private ChesspieceFactory(){

    }

    public static Chesspiece getChesspiece(String color){
        return pools.get(color);
    }
}
