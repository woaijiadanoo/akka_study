import akka.actor.*;
import akka.dispatch.OnSuccess;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.jiangh.akka.demo.ActorCeatorDemo;
import com.jiangh.akka.demo.ActorDemo;
import com.jiangh.akka.demo.forward.ForwardActor;
import com.jiangh.akka.demo.procedure.BecomeActorDemo;
import com.jiangh.akka.demo.stop.StopActorDemo;
import com.jiangh.akka.demo.stop.StopWatchActorDemo;
import com.jiangh.akka.demo.uid.UidActorDemo;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class AkkaMain {

    public static void main(String[] args) {
        // 2.9　停掉一个Actor
        stop();
        // 查找UID
//        uid();

        // 2.7 Actor行为切换
//        procedure();
    }

    // 2.9　停掉一个Actor
    private static void stop(){
        ActorSystem system=ActorSystem. create( "sys");
//        ActorRef ar =system .actorOf(Props.create (StopActorDemo. class), "stopActor");
//        system.stop(ar);
//        ar.tell(PoisonPill.getInstance(),ActorRef.noSender());

        ActorRef ar =system.actorOf(Props.create(StopWatchActorDemo.class), "stopActor");

        ar.tell(Kill.getInstance(),ActorRef. noSender());
    }

    private static void uid(){
        ActorSystem system =ActorSystem. create( "sys" );
        ActorRef ref =system.actorOf(Props.create (UidActorDemo.class), "uidActor");
        ref.tell( "hello" ,ActorRef.noSender());
    }

    /*
        2.7 Actor行为切换
     */
    private static void procedure(){
        ActorSystem system =ActorSystem. create( "sys" );
        ActorRef ref =system.actorOf(Props.create (BecomeActorDemo.class), "becomeActor");
        ref.tell( "hello" ,ActorRef. noSender());
        ref.tell( "hi" , ActorRef. noSender());
        ref.tell( "hi" , ActorRef. noSender());
        ref.tell( "hi" , ActorRef. noSender());
    }


    private static void test(){
        ActorSystem actorSystem = ActorSystem.create("sys");

        // create actor demo
//        ActorRef actorRef = actorSystem.actorOf(Props.create(ActorDemo.class), "actorDemo");
//        ActorRef actorCreateRef = actorSystem.actorOf(ActorCeatorDemo.createProps(), "actorCreatorDemo");

        /*
            sendMessage - 它们都以异步的方式发送消息
            tell - 发完后立即返回
            ask - 期待得到一个返回结果
         */
        // tell
//        actorRef.tell("Hello World!", ActorRef.noSender());
//
//        // ask
//        Timeout timeout = new Timeout(Duration.create(2,"seconds"));
//        Future<Object> askDemo = Patterns.ask(actorCreateRef, "Ask HelloWorld", timeout);
//        askDemo.onSuccess(new OnSuccess<Object>() {
//            @Override
//            public void onSuccess(Object o) throws Throwable {
//                System.out.println("o = " + o);
//            }
//        }, actorSystem.dispatcher());


        // forward
        ActorRef forwardRef = actorSystem.actorOf(Props.create(ForwardActor.class), "forwardDemo");
        Timeout timeout = new Timeout(Duration.create(2,"seconds"));
        Future<Object> forwardDemo = Patterns.ask(forwardRef, "forward HelloWorld", timeout);
        forwardDemo.onSuccess(new OnSuccess<Object>() {
            @Override
            public void onSuccess(Object o) throws Throwable {
                System.out.println("o = " + o);
            }
        }, actorSystem.dispatcher());


//        System.exit(0);
    }

}
