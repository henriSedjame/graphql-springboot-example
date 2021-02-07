package io.github.hsedjame.graphqlspringbootexample.services;

import io.github.hsedjame.graphqlspringbootexample.domain.Asset;
import io.github.hsedjame.graphqlspringbootexample.domain.Client;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    private final Logger logger = Logger.getLogger(BankAccountService.class.getName());

    public Map<UUID, BigDecimal> loadBalances(Set<UUID> ids) {
        return ids.stream().collect(Collectors.toMap(id -> id,this::loadBalanceByAccountId));
    }

    public Map<UUID, Client> loadClients(Set<UUID> ids) {
        return ids.stream()
                .collect(Collectors.toMap(id -> id, this::loadClientByAccountId));
    }

    public Map<UUID, List<Asset>> loadAssets(Set<UUID> ids) {
        return ids.stream()
                .collect(Collectors.toMap(id -> id, this::loadAssetsByAccountId));
    }

    private BigDecimal loadBalanceByAccountId(UUID id){
        logger.info(String.format("Fetching balance for bank account with id %s", id.toString()));
        return BigDecimal.ONE;
    }
    private Client loadClientByAccountId(UUID id){
        logger.info(String.format("Fetching client for bank account with id %s", id.toString()));
        return new Client("", "");
    }

    private List<Asset> loadAssetsByAccountId(UUID id){
        logger.info(String.format("Fetching assets for bank account with id %s", id.toString()));
        return List.of();
    }
}
