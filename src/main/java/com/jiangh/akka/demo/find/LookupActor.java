package com.jiangh.akka.demo.find;

import akka.actor.*;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class LookupActor extends UntypedActor {
    private ActorRef target = null;

    {
        target = getContext().actorOf(Props.create(FindTargetActor.class), "findTargetActor");
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof String) {
            if ("find".equals(msg)) {
                ActorSelection as = getContext().actorSelection("findTargetActor");
                as.tell(new Identify("A001"), getSelf());
            }
        } else if (msg instanceof ActorIdentity) {
            ActorIdentity ai = (ActorIdentity) msg;
            if (ai.correlationId().equals("A001")) {
                ActorRef ref = ai.getRef();
                if (ref != null) {
                    System.out.println("ActorIdentity: " + ai.correlationId() + "" + ref);
                    ref.tell("hello target", getSelf());
                }
            }
        }
    }
}
