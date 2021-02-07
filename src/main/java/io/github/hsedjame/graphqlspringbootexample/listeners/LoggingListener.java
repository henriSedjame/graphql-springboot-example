package io.github.hsedjame.graphqlspringbootexample.listeners;


import graphql.kickstart.servlet.core.GraphQLServletListener;
import io.github.hsedjame.graphqlspringbootexample.listeners.callbacks.LoggingRequestCallback;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Clock;
import java.time.Instant;

@Component
public record LoggingListener(Clock clock) implements GraphQLServletListener {

    @Override
    public RequestCallback onRequest(HttpServletRequest request, HttpServletResponse response) {
        return new LoggingRequestCallback(Instant.now(clock));
    }
}
