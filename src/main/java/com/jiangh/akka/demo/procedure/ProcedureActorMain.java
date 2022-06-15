package com.jiangh.akka.demo.procedure;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class ProcedureActorMain {

    public static void main(String[] args) {
        ActorSystem system =ActorSystem. create( "sys" );
        ActorRef ref =system.actorOf(Props.create (BecomeActorDemo.class), "becomeActor");
        ref.tell( "start" ,ActorRef. noSender());
        ref.tell( "hi" , ActorRef. noSender());
        ref.tell( "hi" , ActorRef. noSender());
        ref.tell( "hi" , ActorRef. noSender());

        ref.tell( "end" ,ActorRef. noSender());
        ref.tell( "hi" , ActorRef. noSender());
        ref.tell( "hi" , ActorRef. noSender());
        ref.tell( "hi" , ActorRef. noSender());
    }

}
