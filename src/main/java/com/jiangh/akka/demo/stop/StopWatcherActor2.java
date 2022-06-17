package com.jiangh.akka.demo.stop;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class StopWatcherActor2 extends UntypedActor {

    ActorRef child = null;

    @Override
    public void preStart() throws Exception {
        //创建子级Actor
        child = getContext().actorOf(Props.create(StopActorDemo.class), "workerActor");
        getContext().watch(child);
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg.equals("stopChild")) {
            System.out.println("stopChild msg = " + msg);
            getContext().stop(child);
        } else if (msg instanceof Terminated) {
            Terminated t = (Terminated) msg;
            System.out.println("监控到" + t.getActor() + "停止了");
        } else {
            unhandled(msg);
        }
    }


    @Override
    public void postStop() throws Exception {
        System.out.println("watchActor stop");
    }
}
