package com.lightbend.akka.sample;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class PongActor extends AbstractBehavior<PongActor.Pong>{

    public static final class Pong {
        public final ActorRef<PingActor.Ping> from;
        Pong(ActorRef<PingActor.Ping> from){
            this.from = from;
        }
    }

    public static Behavior<Pong> create() {
        return Behaviors.setup(context -> new PongActor(context));
    }

    private PongActor(ActorContext<Pong> context){
        super(context);
    }

    @Override
    public Receive<Pong> createReceive() {
        return newReceiveBuilder()
                .onMessage(Pong.class, this::onPongMessage).build();
    }

    private Behavior<Pong> onPongMessage(Pong message){
        System.out.println("Pong!");
        message.from.tell(new PingActor.Ping(getContext().getSelf()));
        return this;
    }
}

