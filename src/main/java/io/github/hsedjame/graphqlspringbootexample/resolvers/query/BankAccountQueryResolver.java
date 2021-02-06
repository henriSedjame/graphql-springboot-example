package io.github.hsedjame.graphqlspringbootexample.resolvers.query;

import graphql.kickstart.tools.GraphQLQueryResolver;
import io.github.hsedjame.graphqlspringbootexample.domain.BankAccount;
import io.github.hsedjame.graphqlspringbootexample.domain.Currency;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BankAccountQueryResolver implements GraphQLQueryResolver {

    public BankAccount bankAccount(UUID id){
        return new BankAccount(UUID.randomUUID(), Currency.EUR);
    }
}
