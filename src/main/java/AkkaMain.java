import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.dispatch.OnSuccess;
import akka.pattern.Patterns;
import akka.util.Timeout;
import com.jiangh.akka.demo.ActorCeatorDemo;
import com.jiangh.akka.demo.ActorDemo;
import com.jiangh.akka.demo.forward.ForwardActor;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

/**
 * @author jiangzheng
 * @version 1.0
 * @description:
 */
public class AkkaMain {

    public static void main(String[] args) {
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
