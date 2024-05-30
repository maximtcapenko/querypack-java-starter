package com.querypack.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;

@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,
  QuerydslPredicateExecutor<T> {
    Optional<T> findOne(Predicate predicate, EntityPathBase<?>...loads);
}
