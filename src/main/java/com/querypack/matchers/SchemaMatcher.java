package com.querypack.matchers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.querypack.entities.Schema;

@Service
public class SchemaMatcher extends BaseMatcher<Schema> {

    private final Map<String, Function<ExampleMatcher, ExampleMatcher>> matchers = new HashMap<>();

    public SchemaMatcher() {
        matchers.put("name", matcher -> matcher.withMatcher("name", match -> match.startsWith()));
        matchers.put("registrationCode", matcher -> matcher.withMatcher("registrationCode", match -> match.exact()));
        matchers.put("contactPerson",
                matcher -> matcher.withIgnoreNullValues().withMatcher("contactPerson", match -> match.exact()));
    }

    @Override
    protected Map<String, Function<ExampleMatcher, ExampleMatcher>> getMatchers() {
        return matchers;
    }

}
