package com.jiangh.akka.demo.supervisor;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.OnSuccess;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class SupervisorMain {

    public static void main(String[] args) {

        ActorSystem system=ActorSystem. create( "sys");
        ActorRef actorRef = system.actorOf(Props.create(SupervisorActor.class), "supervisorActor");

        ActorSelection as = system.actorSelection(actorRef.path()+"/supervisorChildActor");

//        as.tell(new IOException(), ActorRef.noSender());
//        as.tell(new SQLException("SQL异常"), ActorRef.noSender());
        as.tell(new IndexOutOfBoundsException(), ActorRef.noSender());

        as.tell("getValue", actorRef);
    }

}
