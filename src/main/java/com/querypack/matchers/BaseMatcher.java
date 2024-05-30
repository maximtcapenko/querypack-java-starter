package com.querypack.matchers;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.util.ReflectionUtils;

public abstract class BaseMatcher<T> {

    protected abstract Map<String, Function<ExampleMatcher, ExampleMatcher>> getMatchers();

    public Function<ExampleMatcher, ExampleMatcher> Match(T probe) {
        List<Function<ExampleMatcher, ExampleMatcher>> foundMatchers = new ArrayList<Function<ExampleMatcher, ExampleMatcher>>();

        Map<String, Function<ExampleMatcher, ExampleMatcher>> matchers = getMatchers();

        ReflectionUtils.doWithFields(probe.getClass(), field -> {
            String name = field.getName();
            field.setAccessible(true);
            if (field.get(probe) != null && matchers.containsKey(name)) {
                foundMatchers.add(matchers.get(name));
            }
        }, null);

        Function<ExampleMatcher, ExampleMatcher> matcher = null;

        for (Function<ExampleMatcher, ExampleMatcher> function : foundMatchers) {
            if (matcher == null) {
                matcher = function;
            } else {
                final Function<ExampleMatcher, ExampleMatcher> tmp = matcher;
                matcher = m -> tmp.apply(function.apply(m));
            }
        }

        return matcher;
    }
}
