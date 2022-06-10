package com.jiangh.akka.demo.supervisor;

import akka.actor.*;
import akka.actor.SupervisorStrategy.Directive;
import akka.japi.Function;
import scala.concurrent.duration.Duration;

import java.io.IOException;
import java.sql.SQLException;


/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class SupervisorActor extends UntypedActor {

    private SupervisorStrategy strategy = new OneForOneStrategy(3, Duration.create("1 minute"), new Function<Throwable, Directive>() {
        @Override
        public Directive apply(Throwable t) throws Exception {
            if (t instanceof IOException) {
                System.out.println("==========IOException=========");
                return SupervisorStrategy.resume();
            } else if (t instanceof IndexOutOfBoundsException) {
                System.out.println("=========IndexOutOfBoundsException==========");
                return SupervisorStrategy.restart();
            } else if (t instanceof SQLException) {
                System.out.println("==========SQLException=========");
                return SupervisorStrategy.stop();
            } else {
                System.out.println("==========escalate=========");
                return SupervisorStrategy.escalate();
            }
        }
    });


    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void preStart() throws Exception {
        ActorRef supervisorChildActor = getContext().actorOf(Props.create(SupervisorChildActor.class), "supervisorChildActor");
        System.out.println("supervisorChildActor = " + supervisorChildActor.path());
        getContext().watch(supervisorChildActor);
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof Terminated) {
            Terminated ter = (Terminated) msg;
            System.out.println(ter.getActor() + "已经终止");
        } else {
            System.out.println("stateCount=" + msg);
        }
    }
}
