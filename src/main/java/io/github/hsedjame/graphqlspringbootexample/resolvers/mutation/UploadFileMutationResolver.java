package io.github.hsedjame.graphqlspringbootexample.resolvers.mutation;

import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.SelectedField;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.logging.Logger;

@Component
public class UploadFileMutationResolver implements GraphQLMutationResolver {

    private final Logger logger = Logger.getLogger(UploadFileMutationResolver.class.getName());

    public UUID uploadFile(DataFetchingEnvironment environment){

        final DefaultGraphQLServletContext context = environment.getContext();
        context.getFileParts().forEach(p -> logger.info(String.format("Uploading : %s , size : %s", p.getSubmittedFileName(), p.getSize())));

        return UUID.randomUUID();
    }
}
