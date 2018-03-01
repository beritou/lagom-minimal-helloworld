package com.lightbend.lagom.example.hello.impl;

import com.lightbend.lagom.example.hello.api.HelloService;
import com.lightbend.lagom.javadsl.testkit.ServiceTest;
import org.junit.Test;

import static com.lightbend.lagom.javadsl.testkit.ServiceTest.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;

public class HelloServiceTest {

    @Test
    public void saysHello() throws Exception {
        ServiceTest.Setup customStubSetup = defaultSetup()
                .withConfigureBuilder(b -> b.overrides(bind(SaysHello.class).to(GreeterStub.class)));

        withServer(customStubSetup, server -> {
            HelloService service = server.client(HelloService.class);
            String msg = service.hello("Alice").invoke().toCompletableFuture().get(5, SECONDS);
            assertEquals("Hello, Alice!\n", msg); // default greeting
        });
    }

    @Test
    public void saysCustomGreeting() throws Exception {
        ServiceTest.Setup customStubSetup = defaultSetup()
                .withConfigureBuilder(b -> b.overrides(bind(SaysHello.class).to(GreeterStub.class)));

        withServer(customStubSetup, server -> {
            HelloService service = server.client(HelloService.class);
            String expected = "Hello, Alice! This ***IS*** a test!\n";
            String msg = service.customHello("Alice").invoke().toCompletableFuture().get(5, SECONDS);
            assertEquals(expected, msg);
        });
    }

    static class GreeterStub implements SaysHello {
        @Override
        public String sayHello(String name) {
            return "Hello, " + name + "! This ***IS*** a test!\n";
        }
    }
}


