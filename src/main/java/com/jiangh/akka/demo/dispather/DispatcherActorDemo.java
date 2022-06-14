package com.jiangh.akka.demo.dispather;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class DispatcherActorDemo extends UntypedActor {

    private LoggingAdapter logger = Logging.getLogger(this.getContext().system(), this);

    @Override
    public void onReceive(Object msg) throws Exception {
        logger.info("msg : " + msg + " dispatcher :"+getContext().dispatcher().toString());
    }
}
