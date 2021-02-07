package io.github.hsedjame.graphqlspringbootexample.Utils;

import io.github.hsedjame.graphqlspringbootexample.instrumentation.RequestLoggingInstrumentation;
import org.jetbrains.annotations.NotNull;
import org.slf4j.MDC;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public record CorrelationIdPropagationExecutor(Executor delegate) implements Executor {

    public static Executor wrap(Executor executor) {
        return new CorrelationIdPropagationExecutor(executor);
    }

    public static Executor newFixedThreadPool() {
        return wrap(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));
    }

    @Override
    public void execute(@NotNull Runnable command) {
        var correlationId = MDC.get(RequestLoggingInstrumentation.CORRELATION_ID);
        delegate.execute(() -> {
            try {
                MDC.put(RequestLoggingInstrumentation.CORRELATION_ID, correlationId);
                command.run();
            } finally {
                MDC.remove(RequestLoggingInstrumentation.CORRELATION_ID);
            }
        });
    }
}
