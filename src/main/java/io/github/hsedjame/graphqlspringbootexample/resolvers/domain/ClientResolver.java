package io.github.hsedjame.graphqlspringbootexample.resolvers.domain;

import graphql.execution.DataFetcherResult;
import graphql.kickstart.tools.GraphQLResolver;
import io.github.hsedjame.graphqlspringbootexample.domain.BankAccount;
import io.github.hsedjame.graphqlspringbootexample.domain.Client;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ClientResolver implements GraphQLResolver<BankAccount> {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public CompletableFuture<DataFetcherResult<Client>> client(BankAccount account){

        return CompletableFuture.supplyAsync(
                () ->  DataFetcherResult.<Client>newResult()
                        .data( account.client() != null ? account.client():new Client("Henri", "SEDJAME"))
                        .build(),
               executorService
        );
    }
}
