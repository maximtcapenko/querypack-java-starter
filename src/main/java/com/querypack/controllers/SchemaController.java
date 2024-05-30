package com.querypack.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.querypack.entities.Schema;
import com.querypack.entities.Q.QSchema;
import com.querypack.exeptions.NotFoundHttpException;
import com.querypack.matchers.BaseMatcher;
import com.querypack.repositories.SchemaRepository;
import com.querypack.utils.ExampleBuilder;

@RestController
public class SchemaController {

	@Autowired
	private SchemaRepository repository;

	@Autowired
	private BaseMatcher<Schema> matcher;

	@GetMapping("/api/schemas/{id}")
	public Schema findOne(@PathVariable("id") int id) throws NotFoundHttpException {
		Optional<Schema> customer = repository.findOne(QSchema.schema.id.eq(id),
				QSchema.schema.contactPerson);

		if (customer.isEmpty()) {
			throw new NotFoundHttpException();
		}

		return customer.get();
	}

	@GetMapping("/api/schemas/search")
	public Iterable<Schema> findAll(Schema query) {
		return repository.findAll(
			ExampleBuilder.Match(matcher.Match(query),
						query));
	}
}