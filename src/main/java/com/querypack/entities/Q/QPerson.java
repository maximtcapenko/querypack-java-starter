package com.querypack.entities.Q;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;
import com.querypack.entities.Person;
import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;

import com.querydsl.core.types.Path;


/**
 * QPerson is a Querydsl query type for Person
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPerson extends EntityPathBase<Person> {

    private static final long serialVersionUID = -997225627L;

    public static final QPerson person = new QPerson("person");

    public final QBase _super = new QBase(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    //inherited
    public final NumberPath<Integer> id = _super.id;

    public final StringPath lastName = createString("lastName");

    //inherited
    public final DateTimePath<java.util.Date> modifiedAt = _super.modifiedAt;

    public final StringPath phone = createString("phone");

    public QPerson(String variable) {
        super(Person.class, forVariable(variable));
    }

    public QPerson(Path<? extends Person> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPerson(PathMetadata metadata) {
        super(Person.class, metadata);
    }

}

