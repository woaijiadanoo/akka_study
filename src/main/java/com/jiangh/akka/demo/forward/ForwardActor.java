package com.jiangh.akka.demo.forward;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class ForwardActor extends UntypedActor {

    private ActorRef target = getContext().actorOf(Props.create(ForwardTargetActor.class), "targetActor");

    @Override
    public void onReceive(Object msg) throws Exception {
        System.out.println("forward msg = " + msg + "sender : "+ getSender());
        target.forward(msg, getContext());
    }
}
