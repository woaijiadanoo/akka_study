package com.jiangh.akka.demo.find;

import akka.actor.*;
import akka.dispatch.OnSuccess;
import akka.util.Timeout;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class FindActorMain {

    public static void main(String[] args) {
//        find01();
//        find02();
        find03();
    }

    private static void find01(){
        ActorSystem actorSystem = ActorSystem.create("sys");

        ActorRef actorRef = actorSystem.actorOf(Props.create(FindTargetActor.class), "findTarget");
        System.out.println("actorRef.path() = " + actorRef.path());
        actorRef.tell("findTarget", ActorRef.noSender());

        ActorSelection actorSelection = actorSystem.actorSelection("/user/findTarget");
        System.out.println("actorSelection.path() = " + actorSelection.pathString());
        actorSelection.tell("selection findTarget", ActorRef.noSender());
    }

    private static void find02(){
        ActorSystem actorSystem = ActorSystem.create("sys");

        ActorRef actorRef = actorSystem.actorOf(Props.create(LookupActor.class), "lookup");
        actorRef.tell("find", ActorRef.noSender());

    }

    private static void find03(){
        ActorSystem actorSystem = ActorSystem.create("sys");
        actorSystem.actorOf(Props.create(FindTargetActor.class), "findTarget");

        ActorSelection actorSelection = actorSystem.actorSelection("/user/findTarget");
        System.out.println("actorSelection.path() = " + actorSelection.pathString());
        actorSelection.tell("selection findTarget", ActorRef.noSender());

        Timeout timeout = new Timeout(Duration.create(20,"seconds"));
        Future<ActorRef> actorRefFuture = actorSelection.resolveOne(timeout);
        actorRefFuture.onSuccess(new OnSuccess<ActorRef>() {
            @Override
            public void onSuccess(ActorRef actorRef) throws Throwable {
                System.out.println("actorRef = " + actorRef);

                actorRef.tell("onsuccess find", ActorRef.noSender());
            }
        }, actorSystem.dispatcher());

    }

}
