package com.jiangh.akka.demo.supervisor;

import akka.actor.UntypedActor;
import scala.Option;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class SupervisorChildActor extends UntypedActor {
    //状态数据
    private int stateCount = 1;

    public void preStart() throws Exception {
        super.preStart();
        System.out.println("worker actor preStart");
    }

    @Override
    public void postStop() throws Exception {
        super.postStop();
        System.out.println("worker actor postStop");
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        System.out.println("worker actor preRestart begin" + this.stateCount);
        super.preRestart(reason, message);
        System.out.println("worker actor preRestart end" + this.stateCount);
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        System.out.println("worker actor post Restart begin" + this.stateCount);
        super.postRestart(reason);
        System.out.println("worker actor postRestart end" + this.stateCount);
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        //模拟计算任务
        this.stateCount++;
        System.out.println("onReceive stateCount = " + stateCount);
        if (msg instanceof Exception) {
            throw (Exception) msg;
        } else if ("getValue".equals(msg)) {
            //返回当前数据
            getSender().tell(stateCount, getSelf());
        } else {
            unhandled(msg);
        }
    }

}
