package com.jiangh.akka.demo.circuitbreaker;

import akka.actor.*;
import akka.pattern.CircuitBreaker;
import scala.concurrent.duration.Duration;
import akka.actor.SupervisorStrategy.Directive;
import akka.japi.Function;

import java.util.concurrent.Callable;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class CircuitBreakerActor extends UntypedActor {
    private ActorRef workerChild;
    private static SupervisorStrategy strategy = new
            OneForOneStrategy(20, Duration.create("1 minute"), new Function<Throwable, Directive>() {
        @Override
        public Directive apply(Throwable t) {
            return SupervisorStrategy.resume();
        }
    });


    @Override
    public SupervisorStrategy supervisorStrategy() {
        return strategy;
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        workerChild = getContext().actorOf(
                Props.create(WorkerActor.class), "workerActor");
    }

    @Override
    public void onReceive(Object message) throws
            Exception {
        workerChild.tell(message, getSender());
    }
}

class WorkerActor extends UntypedActor {
    private CircuitBreaker breaker;

    @Override
    public void preStart() throws Exception {
        super.preStart();
        this.breaker = new CircuitBreaker(
                getContext().dispatcher(),
                getContext().system().scheduler(), 5,
                Duration.create(2, "s"),
                Duration.create(1, "min")).onOpen(new Runnable() {
            public void run() {
                System.out.println(System.currentTimeMillis() + "--->Actor CircuitBreaker 开启");
            }
        }).onHalfOpen(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() + "--->Actor CircuitBreaker 半开启");
            }
        }).onClose(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() + "--->Actor CircuitBreaker关闭");
            }
        });
    }

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String msg = (String) message;
            if (msg.startsWith("sync")) {
                getSender().tell(breaker.callWithSyncCircuitBreaker(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("msg:" + msg);
                        Thread.sleep(3000);
                        return msg;
                    }
                }), getSelf());
            }
        }
    }
}
