package at.htl;

import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import io.vertx.mutiny.core.eventbus.Message;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.Executor;

@ApplicationScoped
public class GreetingResource {
    @Inject
    Executor executor;

    @ConsumeEvent("greeting")
    public String consume(String name){
        return name.toUpperCase();
    }

    @ConsumeEvent("greeting")
    public Uni<String> consume2(String name){
        return Uni.createFrom().item(() -> name.toUpperCase()).emitOn(executor);
    }

    @ConsumeEvent("greeting")
    public void consume3(Message<String> msg){
        System.out.println(msg.address());
        System.out.println(msg.body());
    }
}