package io.github.hsedjame.graphqlspringbootexample.listeners.callbacks;

import graphql.kickstart.servlet.core.GraphQLServletListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

public class LoggingRequestCallback implements GraphQLServletListener.RequestCallback {

    private final Logger logger = Logger.getLogger(LoggingRequestCallback.class.getName());

    private final Instant startTime;

    public LoggingRequestCallback(Instant startTime) {
        this.startTime = startTime;
    }

    @Override
    public void onSuccess(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void onError(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {

    }

    @Override
    public void onFinally(HttpServletRequest request, HttpServletResponse response) {
        logger.info(String.format("Request completed in %d milliseconds", Duration.between(startTime, Instant.now()).getNano()/1000000));
    }
}
