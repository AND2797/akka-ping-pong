package com.lightbend.akka.sample;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

//public class PingActor extends AbstractBehavior<PingPong.Command> {
//
////    public static class Ping implements PingPong.Command {
////
////    }
//
//
//
//    public static Behavior<PingPong.Command> create() {
//        return Behaviors.setup(context -> new PingActor(context));
//    }
//
//    private PingActor(ActorContext<PingPong.Command> context) {
//        super(context);
//    }
//
//    @Override
//    public Receive<PingPong.Command> createReceive() {
////        return newReceiveBuilder().onMessageEquals(PingPong.Ping.INSTANCE, this::onPingMessage).build();
//        return newReceiveBuilder().onMessage(PingPong.Command.class, this::onPingMessage).build();
//    }
//
//    private Behavior<PingPong.Command> onPingMessage(){
//        System.out.println("ok!");
//        return this;
//    }
//
//}

public class PingActor extends AbstractBehavior<PingActor.Ping>{

    public static final class Ping {
        public final ActorRef<PongActor.Pong> from;
        Ping(ActorRef<PongActor.Pong> from){
            this.from = from;
        }
    }

    public static Behavior<Ping> create() {
        return Behaviors.setup(context -> new PingActor(context));
    }

    private PingActor(ActorContext<Ping> context){
        super(context);
    }

    @Override
    public Receive<Ping> createReceive() {
        return newReceiveBuilder()
                .onMessage(Ping.class, this::onPingMessage).build();
    }

    private Behavior<Ping> onPingMessage(Ping message){
        System.out.println("Ping!");
        message.from.tell(new PongActor.Pong(getContext().getSelf()));
        return this;
    }
}

