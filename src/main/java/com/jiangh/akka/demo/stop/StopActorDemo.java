package com.jiangh.akka.demo.stop;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class StopActorDemo extends UntypedActor {
    LoggingAdapter log = Logging.getLogger( this .getContext().system(), this);

    @Override
    public void onReceive(Object msg) throws Exception {
        log.info( "收到消息：" + msg );
    }

    @Override
    public void postStop() throws Exception {
        log.info( "StopActorDemo Worker postStop" );
    }
}
