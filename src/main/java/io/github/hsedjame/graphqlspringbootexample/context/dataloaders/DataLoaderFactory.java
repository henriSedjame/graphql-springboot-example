package io.github.hsedjame.graphqlspringbootexample.context.dataloaders;


import io.github.hsedjame.graphqlspringbootexample.Utils.CorrelationIdPropagationExecutor;
import io.github.hsedjame.graphqlspringbootexample.domain.Asset;
import io.github.hsedjame.graphqlspringbootexample.domain.Client;
import io.github.hsedjame.graphqlspringbootexample.services.BankAccountService;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Component
public record DataLoaderFactory(BankAccountService bankAccountService) {
    public static final String BALANCE_LOADER = "BALANCE_LOADER";
    public static final String CLIENT_LOADER = "CLIENT_LOADER";
    public static final String ASSETS_LOADER = "ASSETS_LOADER";
    private static final Executor executor = CorrelationIdPropagationExecutor.newFixedThreadPool();

    public DataLoaderRegistry create() {

        var registry = new DataLoaderRegistry();

        registry.register(BALANCE_LOADER,createBalanceLoader());
        registry.register(CLIENT_LOADER,createClientLoader());
        registry.register(ASSETS_LOADER,createAssetsLoader());

        return registry;
    }

    private DataLoader<UUID, BigDecimal>  createBalanceLoader(){
        return DataLoader.newMappedDataLoader(ids ->
            CompletableFuture.supplyAsync(() -> bankAccountService.loadBalances(ids),
                    executor)
        );
    }


    private DataLoader<UUID, Client>  createClientLoader() {
        return DataLoader.newMappedDataLoader(ids ->
                CompletableFuture.supplyAsync(() -> bankAccountService.loadClients(ids),
                        executor)
        );
    }

    private DataLoader<UUID, List<Asset>>  createAssetsLoader() {
        return DataLoader.newMappedDataLoader(ids ->
                CompletableFuture.supplyAsync(() -> bankAccountService.loadAssets(ids),
                        executor)
        );
    }
}
