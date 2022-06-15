package com.jiangh.akka.demo.find;

import akka.actor.UntypedActor;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class FindTargetActor extends UntypedActor {

    @Override
    public void onReceive(Object msg) throws Exception {
        System.out.println("FindTargetActor o = " + msg);
        System.out.println("FindTargetActor path = " + getSelf().path());
        System.out.println("FindTargetActor uid = " + getSelf().path().uid());
        System.out.println("FindTargetActor name = " + getSelf().path().name());
    }
}
