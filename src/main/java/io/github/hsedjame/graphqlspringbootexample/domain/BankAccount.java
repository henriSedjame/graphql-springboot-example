package io.github.hsedjame.graphqlspringbootexample.domain;

import java.util.List;
import java.util.UUID;

public record BankAccount(UUID id, Client client, Currency currency, List<Asset> assets) {
    public BankAccount(UUID id, Currency currency) {
        this(id, null, currency,null);
    }

}
