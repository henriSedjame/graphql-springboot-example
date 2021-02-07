package io.github.hsedjame.graphqlspringbootexample.resolvers.query;

import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

import io.github.hsedjame.graphqlspringbootexample.TestApplication;
import io.micrometer.core.instrument.util.IOUtils;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestApplication.class)
class BankAccountQureyTest {

    public static final String GRAPHQL_REQUEST_PATH = "graphql/query/request/%s.graphql";
    public static final String GRAPHQL_RESPONSE_PATH = "graphql/query/response/%s.json";

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void bankAccount() throws IOException, JSONException {
        var testName = "bank_account";
        final GraphQLResponse response = graphQLTestTemplate.postForResource(format(GRAPHQL_REQUEST_PATH, testName));
        final String expectedResponseBody = read(testName);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        JSONAssert.assertEquals(expectedResponseBody,response.getRawResponse().getBody(), false);

    }

    @Test
    void bankAccounts() {
    }

    private String read(String location) throws IOException {
        return IOUtils.toString(
                new ClassPathResource(format(GRAPHQL_RESPONSE_PATH,location)).getInputStream(),
                StandardCharsets.UTF_8);
    }
}
