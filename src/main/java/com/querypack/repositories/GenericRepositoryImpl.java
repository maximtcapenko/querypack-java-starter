package com.querypack.repositories;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class GenericRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements GenericRepository<T, ID> {

    private static final EntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
    private final JPAQueryFactory jpaFactory;
    private final QuerydslPredicateExecutor<T> querydslPredicateExecutor;

    public GenericRepositoryImpl(
            JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        
        this.jpaFactory = new JPAQueryFactory(entityManager);
        this.querydslPredicateExecutor = new QuerydslJpaPredicateExecutor<>(entityInformation, entityManager, resolver,
                null);
    }

    public Optional<T> findOne(Predicate predicate, EntityPathBase<?>... loads) {
        JPAQuery<T> query = this.jpaFactory.selectFrom(resolver.createPath(this.getDomainClass())).where(predicate);

        for (EntityPathBase<?> load : loads) {
            ManyToOne annotation = load.getAnnotatedElement().getAnnotation(ManyToOne.class);
            if (annotation != null) {
                FetchType type = annotation.fetch();

                if (type == FetchType.LAZY) {
                    query.leftJoin(load);
                } else {
                    query.innerJoin(load);
                }
            }
        }
        return Optional.ofNullable(query.fetchOne());
    }

    @Override
    public Optional<T> findOne(Predicate predicate) {
        return this.querydslPredicateExecutor.findOne(predicate);
    }

    @Override
    public Iterable<T> findAll(Predicate predicate) {
        return this.querydslPredicateExecutor.findAll(predicate);
    }

    @Override
    public Iterable<T> findAll(Predicate predicate, Sort sort) {
        return this.querydslPredicateExecutor.findAll(predicate, sort);
    }

    @Override
    public Iterable<T> findAll(Predicate predicate, OrderSpecifier<?>... orders) {
        return this.querydslPredicateExecutor.findAll(predicate, orders);
    }

    @Override
    public Iterable<T> findAll(OrderSpecifier<?>... orders) {
        return this.querydslPredicateExecutor.findAll(orders);
    }

    @Override
    public Page<T> findAll(Predicate predicate, Pageable pageable) {
        return this.querydslPredicateExecutor.findAll(predicate, pageable);
    }

    @Override
    public long count(Predicate predicate) {
        return this.querydslPredicateExecutor.count(predicate);
    }

    @Override
    public boolean exists(Predicate predicate) {
        return this.querydslPredicateExecutor.exists(predicate);
    }

    @Override
    public <S extends T, R> R findBy(Predicate predicate, Function<FetchableFluentQuery<S>, R> queryFunction) {
        return this.querydslPredicateExecutor.findBy(predicate, queryFunction);
    }
}