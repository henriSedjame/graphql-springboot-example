package io.github.hsedjame.graphqlspringbootexample.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record BankAccount(UUID id,
                          Client client,
                          Currency currency,
                          List<Asset> assets,
                          BigDecimal balance) {

    public BankAccount(UUID id, Currency currency) {
        this(id, null, currency,null, null);
    }

}
