package io.github.hsedjame.graphqlspringbootexample.context.dataloaders;


import io.github.hsedjame.graphqlspringbootexample.services.BalanceService;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public record DataLoaderFactory(BalanceService balanceService) {
    public static final String BALANCE_LOADER = "BALANCE_LOADER";
    private static final Executor balanceThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public DataLoaderRegistry create() {

        var registry = new DataLoaderRegistry();

        registry.register(BALANCE_LOADER,createBalanceLoader());

        return registry;
    }

    private DataLoader<UUID, BigDecimal>  createBalanceLoader() {
        return DataLoader.newMappedDataLoader(ids ->
            CompletableFuture.supplyAsync(() -> balanceService.loadBalances(ids),
                    balanceThreadPool )
        );
    }
}
