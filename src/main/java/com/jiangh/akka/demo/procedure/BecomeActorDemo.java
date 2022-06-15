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
            if("end".equals(message)){
                getContext().unbecome();
            }
            System.out.println("procedure become: " +message );
        }
    };

    @Override
    public void onReceive(Object msg) throws Exception {
        System.out.println("接收消息: " +msg );
        if(msg instanceof String){
            if("start".equals(msg)){
                getContext().become(proce);
            }

            System.out.println("un become msg : "+ msg);

        }else{
            unhandled(msg);
        }

    }
}
