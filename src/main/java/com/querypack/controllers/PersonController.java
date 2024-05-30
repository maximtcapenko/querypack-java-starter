package com.querypack.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.querypack.entities.Person;
import com.querypack.matchers.BaseMatcher;
import com.querypack.repositories.PersonRepository;
import com.querypack.utils.ExampleBuilder;

@RestController
public class PersonController {

	@Autowired
	private PersonRepository repository;

	@Autowired
	private BaseMatcher<Person> matcher;

	@GetMapping("/api/persons/search")
	public Iterable<Person> findAll(Principal user, Person query) {
		return repository.findAll(
				ExampleBuilder.MatchAny(matcher.Match(query), query));
	}
}
