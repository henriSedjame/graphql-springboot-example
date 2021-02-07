package io.github.hsedjame.graphqlspringbootexample.domain.repository;

import io.github.hsedjame.graphqlspringbootexample.domain.BankAccount;
import io.github.hsedjame.graphqlspringbootexample.domain.Currency;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BankAccountRepository {

    private final List<BankAccount> accounts;

    public BankAccountRepository() {
        accounts = List.of(
                new BankAccount(
                        UUID.fromString("1565ce84-f06b-47c8-a511-4e22d19d5a70"),
                        Currency.EUR
                ),
                new BankAccount(
                        UUID.fromString("52e73104-243d-4579-8622-a6fca2a8009f"),
                        Currency.USD
                ),
                new BankAccount(
                        UUID.fromString("fc72223b-ca89-415c-b974-8abd99e5f61a"),
                        Currency.EUR
                ),
                new BankAccount(
                        UUID.fromString("ba4bc99b-c4c3-4661-afc4-09df6190af29"),
                        Currency.EUR
                ),
                new BankAccount(
                        UUID.fromString("96770310-4bb4-43b7-8132-6bc915714df4"),
                        Currency.USD
                ),
                new BankAccount(
                        UUID.fromString("ff64bbcc-8117-4899-9768-89242f6a27a2"),
                        Currency.EUR
                )
        ) .stream()
                .sorted(Comparator.comparing(BankAccount::id))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<BankAccount> getAll() {
        return accounts;
    }

    public List<BankAccount> getAllAfter(UUID id){
        return accounts.stream()
                .dropWhile(a -> a.id().compareTo(id) != 1)
                .collect(Collectors.toUnmodifiableList());
    };
}
