package com.querypack.matchers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.querypack.entities.Person;

@Service
public class PersonMatcher extends BaseMatcher<Person> {
    
    private final Map<String, Function<ExampleMatcher, ExampleMatcher>> matchers = new HashMap<>();

    public PersonMatcher() {
        matchers.put("firstName", matcher -> matcher.withMatcher("firstName", match -> match.startsWith()));
        matchers.put("email", matcher -> matcher.withMatcher("email", match -> match.startsWith()));
        matchers.put("phone", matcher -> matcher.withMatcher("phone", match -> match.startsWith()));
    }

    @Override
    protected Map<String, Function<ExampleMatcher, ExampleMatcher>> getMatchers() {
        return matchers;
    }
}
