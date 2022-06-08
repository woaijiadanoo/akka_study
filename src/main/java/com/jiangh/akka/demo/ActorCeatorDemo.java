package com.jiangh.akka.demo;

import akka.actor.Actor;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Creator;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class ActorCeatorDemo extends UntypedActor {

    private LoggingAdapter logger = Logging.getLogger(this.getContext().system(), this);

    @Override
    public void onReceive(Object msg) throws Exception {
        if(msg instanceof String){
            logger.info(msg.toString());
            System.out.println("sender : " + getSender());
            getSender().tell("add "+msg, getSelf());
        } else {
            unhandled(msg);
        }
    }

    public static Props createProps(){
        return Props.create(new Creator<Actor>() {
            @Override
            public Actor create() throws Exception {
                return new ActorCeatorDemo();
            }
        });
    }

}
