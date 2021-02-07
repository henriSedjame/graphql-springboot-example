package io.github.hsedjame.graphqlspringbootexample.resolvers.domain;

import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import io.github.hsedjame.graphqlspringbootexample.context.dataloaders.DataLoaderFactory;
import io.github.hsedjame.graphqlspringbootexample.domain.BankAccount;
import org.dataloader.DataLoader;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class BankAccountResolver implements GraphQLResolver<BankAccount> {

    public CompletableFuture<BigDecimal> balance(BankAccount account, DataFetchingEnvironment environment){

        final DataLoader<UUID, BigDecimal> balanceLoader = environment.getDataLoader(DataLoaderFactory.BALANCE_LOADER);

       return balanceLoader.load(account.id());
    }
}
