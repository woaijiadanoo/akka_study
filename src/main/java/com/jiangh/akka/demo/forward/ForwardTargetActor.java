package com.jiangh.akka.demo.forward;

import akka.actor.UntypedActor;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class ForwardTargetActor extends UntypedActor {
    @Override
    public void onReceive(Object o) throws Exception {
        System.out.println("TargetActor receive: " + o + "，sender=" + getSender());
        getSender().tell("forward "+o, getSelf());
    }
}
