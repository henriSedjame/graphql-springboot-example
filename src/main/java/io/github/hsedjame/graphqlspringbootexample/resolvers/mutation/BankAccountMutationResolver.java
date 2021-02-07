package io.github.hsedjame.graphqlspringbootexample.resolvers.mutation;

import graphql.kickstart.tools.GraphQLMutationResolver;
import io.github.hsedjame.graphqlspringbootexample.domain.BankAccount;
import io.github.hsedjame.graphqlspringbootexample.domain.Client;
import io.github.hsedjame.graphqlspringbootexample.domain.Currency;
import io.github.hsedjame.graphqlspringbootexample.resolvers.mutation.inputs.CreateBankAccountInput;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BankAccountMutationResolver implements GraphQLMutationResolver {

    public BankAccount createBankAccount(CreateBankAccountInput input){
        return new BankAccount(UUID.randomUUID(), new Client(input.firstname(), input.lastname()), Currency.EUR, null, null);
    }

}
