package io.github.hsedjame.graphqlspringbootexample.resolvers.query;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.*;
import io.github.hsedjame.graphqlspringbootexample.domain.BankAccount;
import io.github.hsedjame.graphqlspringbootexample.domain.Currency;
import io.github.hsedjame.graphqlspringbootexample.domain.repository.BankAccountRepository;
import io.github.hsedjame.graphqlspringbootexample.pagination.CursorUtils;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public record BankAccountQueryResolver(BankAccountRepository bankAccountRepository) implements GraphQLQueryResolver {

    public BankAccount bankAccount(UUID id){
        return new BankAccount(UUID.randomUUID(), Currency.EUR);
    }

    public Connection<BankAccount> bankAccounts(int limit, @Nullable String cursor){
        var accounts = cursor == null ? bankAccountRepository.getAll() : bankAccountRepository.getAllAfter(CursorUtils.idFromCurson(cursor));

       List<Edge<BankAccount>> edges = accounts.stream()
                .map(acc -> new DefaultEdge<>(acc, CursorUtils.createCursorFromId(acc.id())))
                .limit(limit)
                .collect(Collectors.toList());

        var pageInfo = new DefaultPageInfo(
                CursorUtils.firstCursorFromEdges(edges),
                CursorUtils.lastCursorFromEdges(edges),
                cursor != null,
                edges.size() >= limit);

        return new DefaultConnection<>(edges, pageInfo);
    }


}
