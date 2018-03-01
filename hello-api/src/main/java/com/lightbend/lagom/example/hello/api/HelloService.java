package com.lightbend.lagom.example.hello.api;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.pathCall;

/**
 * The Hello service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the Hello.
 */
public interface HelloService extends Service {

    /**
     * //TODO: PR this into Tim's repo (the removal of /api/)
     http://localhost:9000/hello/Alice
     */
    ServiceCall<NotUsed, String> hello(String id);
    ServiceCall<NotUsed, String> customHello(String id);

    @Override
    default Descriptor descriptor() {
        return named("hello")
                .withCalls(
                        pathCall("/hello/:id", this::hello),
                        pathCall("/customHello/:id", this::customHello)
                )
                .withAutoAcl(true);
    }
}
