package com.lyp.learn.dp.pattern.flyweight2;

/**
 *
 */
public class Client {
    public static void main(String[] args) {
        Chesspiece c1 = ChesspieceFactory.getChesspiece("黑");
        c1.put(1,1);

        Chesspiece c2 = ChesspieceFactory.getChesspiece("白");
        c2.put(1,2);

        Chesspiece c3 = ChesspieceFactory.getChesspiece("黑");
        c3.put(3,3);

        Chesspiece c4 = ChesspieceFactory.getChesspiece("白");
        c4.put(4,4);
    }
}
