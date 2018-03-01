package com.lightbend.lagom.example.hello.impl;

import akka.NotUsed;
import com.google.inject.Inject;
import com.lightbend.lagom.example.hello.api.HelloService;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import java.util.concurrent.CompletableFuture;

public class HelloServiceImpl implements HelloService {

    private final SaysHello greeter;

    @Inject
    public HelloServiceImpl(SaysHello greeter) {
        this.greeter = greeter;
    }

    @Override
    public ServiceCall<NotUsed, String> hello(String id) {
        return request -> CompletableFuture.completedFuture("Hello, " + id + "!\n");
    }

    @Override
    public ServiceCall<NotUsed, String> customHello(String id) {
        return request -> CompletableFuture.completedFuture(greeter.sayHello(id));
    }

}
