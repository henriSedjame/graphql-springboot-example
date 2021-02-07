package io.github.hsedjame.graphqlspringbootexample.pagination;

import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;


public class CursorUtils {

    public static ConnectionCursor createCursorFromId(UUID id){
        return new DefaultConnectionCursor(
                Base64.getEncoder().encodeToString(id.toString().getBytes(StandardCharsets.UTF_8))
        );
    }

    public static UUID idFromCurson(String cursor) {
        return UUID.fromString(new String(Base64.getDecoder().decode(cursor)));
    }

    public static <T> ConnectionCursor firstCursorFromEdges(List<Edge<T>> edges) {
        return edges.isEmpty() ? null : edges.get(0).getCursor();
    }

    public static <T> ConnectionCursor lastCursorFromEdges(List<Edge<T>> edges) {
        return edges.isEmpty() ? null : edges.get(edges.size() -1).getCursor();
    }
}
