package com.jiangh.akka.demo.procedure;

import akka.actor.UntypedActor;
import akka.japi.Procedure;

/**
 * @author jiangzheng
 * @version 1.0
 * @description: 状态切换
 */
public class BecomeActorDemo extends UntypedActor {

    Procedure<Object> proce= new Procedure<Object>() {
        @Override
        public void apply(Object message) {
            System. out .println("become: " +message );
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        System.out.println("接收消息: " +msg );
        getContext().become( proce);
        System. out .println("-------------------------" );
    }
}
