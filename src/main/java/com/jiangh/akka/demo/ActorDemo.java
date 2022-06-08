package com.jiangh.akka.demo;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class ActorDemo extends UntypedActor {

    private LoggingAdapter logger = Logging.getLogger(this.getContext().system(), this);

    @Override
    public void onReceive(Object msg) throws Exception {
        if(msg instanceof String){
            logger.info(msg.toString());
        } else {
            unhandled(msg);
        }
    }
}
