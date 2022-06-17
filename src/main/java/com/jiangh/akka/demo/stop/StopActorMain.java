package com.jiangh.akka.demo.stop;

import akka.actor.*;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class StopActorMain {

    public static void main(String[] args) {
        stop2();
    }


    // 2.9　停掉一个Actor
    private static void stop(){
        ActorSystem system=ActorSystem. create( "sys");

        ActorRef ar =system.actorOf(Props.create(StopWatchActorDemo.class), "stopActor");

        ar.tell(Kill.getInstance(),ActorRef. noSender());
    }

    private static void stop2(){
        ActorSystem system=ActorSystem. create( "sys");

        ActorRef ar =system.actorOf(Props.create(StopWatcherActor2.class), "stopActor");

        ar.tell("stopChild",ActorRef. noSender());
    }

}
