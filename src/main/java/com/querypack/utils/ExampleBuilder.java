package com.querypack.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.PropertySpecifiers;
import org.springframework.util.ReflectionUtils;

public class ExampleBuilder {
	public static <T> Example<T> MatchAny(Function<ExampleMatcher, ExampleMatcher> configurer, T probe) {
		return MatchInternal(configurer.apply(ExampleMatcher.matchingAny()), probe);
	}

	public static <T> Example<T> Match(Function<ExampleMatcher, ExampleMatcher> configurer, T probe) {
		return MatchInternal(configurer.apply(ExampleMatcher.matching().withIncludeNullValues()), probe);
	}

	private static <T> Example<T> MatchInternal(ExampleMatcher matcher, T probe) {
		PropertySpecifiers specifiers = matcher.getPropertySpecifiers();

		List<String> ignorePaths = new ArrayList<>();

		ReflectionUtils.doWithFields(probe.getClass(), field -> {
			String name = field.getName();
			if (!specifiers.hasSpecifierForPath(name)) {
				ignorePaths.add(name);
			}
		}, null);

		if (!ignorePaths.isEmpty()) {
			matcher = matcher.withIgnorePaths(ignorePaths.toArray(new String[0]));
		}
		return Example.of(probe, matcher);
	}
}