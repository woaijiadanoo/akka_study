package com.jiangh.akka.demo.find;

import akka.actor.UntypedActor;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class FindTargetActor extends UntypedActor {

    @Override
    public void onReceive(Object o) throws Exception {
        System.out.println("FindTargetActor o = " + o);
    }
}
