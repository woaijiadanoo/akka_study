package com.jiangh.akka.demo.uid;

import akka.actor.UntypedActor;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class UidActorDemo extends UntypedActor {
    @Override
    public void onReceive(Object msg) throws Exception {
        System.out.println("getSelf().path().uid() = " + getSelf().path().uid());
        System.out.println("getSelf().path() = " + getSelf().path());
    }
}
