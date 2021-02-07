package io.github.hsedjame.graphqlspringbootexample.context;

import graphql.kickstart.execution.context.GraphQLContext;
import graphql.kickstart.execution.context.GraphQLContextBuilder;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import io.github.hsedjame.graphqlspringbootexample.context.dataloaders.DataLoaderFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;

@Component
public record CustomContextBuilder(DataLoaderFactory dataLoaderFactory) implements GraphQLServletContextBuilder {

    @Override
    public GraphQLContext build(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        var userId = httpServletRequest.getHeader("user_id");

        var context = DefaultGraphQLServletContext.createServletContext()
                .with(httpServletRequest)
                .with(httpServletResponse)
                .with(dataLoaderFactory.create())
                .build();

        return new CustomContext(userId, context);
    }

    @Override
    public GraphQLContext build(Session session, HandshakeRequest handshakeRequest) {
        throw new IllegalArgumentException("Unsupported");
    }

    @Override
    public GraphQLContext build() {
        throw new IllegalArgumentException("Unsupported");
    }
}
