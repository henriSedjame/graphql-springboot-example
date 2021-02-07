package io.github.hsedjame.graphqlspringbootexample.services;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BalanceService {
    public Map<UUID, BigDecimal> loadBalances(Set<UUID> ids) {
        return ids.stream().collect(Collectors.toMap(id -> id,id -> BigDecimal.ONE));
    }
}
