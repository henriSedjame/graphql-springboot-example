package io.github.hsedjame.graphqlspringbootexample.instrumentation;

import graphql.ExecutionResult;
import graphql.execution.instrumentation.InstrumentationContext;
import graphql.execution.instrumentation.SimpleInstrumentation;
import graphql.execution.instrumentation.SimpleInstrumentationContext;
import graphql.execution.instrumentation.parameters.InstrumentationExecutionParameters;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

@Component
public class RequestLoggingInstrumentation extends SimpleInstrumentation {

    public static final String CORRELATION_ID = "correlation_id";
    private final Logger logger = Logger.getLogger(RequestLoggingInstrumentation.class.getName());

    private final Clock clock;

    public RequestLoggingInstrumentation(Clock clock) {
        this.clock = clock;
    }

    @Override
    public InstrumentationContext<ExecutionResult> beginExecution(InstrumentationExecutionParameters parameters) {

        var startTime = Instant.now(clock);

        MDC.put(CORRELATION_ID, parameters.getExecutionInput().getExecutionId().toString());

        logger.info(String.format("Query: %s with parameters: %s",parameters.getQuery(), parameters.getVariables()));

        return SimpleInstrumentationContext.whenCompleted((result, error) -> {
            var duration = Duration.between(Instant.now(clock),startTime).getNano() / 1_000_000;
            if (error == null)
                logger.info(String.format("Completed successfully in %d milliseconds",duration));
            else
                logger.warning(String.format("Failed in %d", duration));

            MDC.remove(CORRELATION_ID);
        });
    }
}
