package io.github.hsedjame.graphqlspringbootexample.resolvers.domain;

import graphql.execution.DataFetcherResult;
import graphql.kickstart.tools.GraphQLResolver;
import io.github.hsedjame.graphqlspringbootexample.domain.Asset;
import io.github.hsedjame.graphqlspringbootexample.domain.BankAccount;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class AssetResolver implements GraphQLResolver<BankAccount> {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public CompletableFuture<DataFetcherResult<List<Asset>>> assets(BankAccount account){
        return CompletableFuture.supplyAsync(
                () -> DataFetcherResult.<List<Asset>>newResult()
                        .data(Arrays.asList(new Asset("asset1"), new Asset("asset2")))
                .build(),
                executorService
        );
    }
}
