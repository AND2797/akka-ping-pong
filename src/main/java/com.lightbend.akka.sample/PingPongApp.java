package com.lightbend.akka.sample;

import akka.actor.typed.ActorSystem;
//TODO: 1. pingPongOrch sends "ping" or "pong" message to Ping and Pong actors, they then reflect messages

public class  PingPongApp {
    public static void main(String[] args){
        ActorSystem<PingActor.Ping> pingtor = ActorSystem.create(PingActor.create(), "pingtor");
        ActorSystem<PongActor.Pong> pongtor = ActorSystem.create(PongActor.create(), "pongtor");
        pingtor.tell(new PingActor.Ping(pongtor));
    }
}