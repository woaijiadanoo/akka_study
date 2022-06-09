package com.jiangh.akka.demo.stop;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class StopWatchActorDemo extends UntypedActor {

    LoggingAdapter log = Logging.getLogger( this .getContext().system(), this);

    ActorRef child= null ;

    @Override
    public void preStart() throws Exception {
        //创建子级Actor
        child=getContext().actorOf(Props.create(StopActorDemo. class), "workerActor" );
    }

    @Override
    public void onReceive(Object msg) throws Exception {

    }

    @Override
    public void postStop() throws Exception {
        log.info( "StopWatchActorDemo WatchActor postStop" );
    }

}
