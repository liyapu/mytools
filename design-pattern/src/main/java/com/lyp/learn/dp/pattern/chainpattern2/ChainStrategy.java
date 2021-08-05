package com.lyp.learn.dp.pattern.chainpattern2;

import java.util.concurrent.RejectedExecutionException;

/**
 * @author liyapu
 * @date 2021-08-05 19:27
 * @desc
 */
public enum ChainStrategy {
    PASS,
    REJECT{
        //节点针对自己无法处理场景的处理策略
        @Override
        public void check(boolean canHandle){
            if(!canHandle){
                throw new RejectedExecutionException();
            }
        }
    };

    void check(boolean canHandle){
        return;
    }
}
