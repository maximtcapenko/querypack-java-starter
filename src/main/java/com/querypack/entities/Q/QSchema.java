package com.querypack.entities.Q;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import com.querypack.entities.Schema;
import com.querypack.entities.Enums.SchemaState;

/**
 * QCustomer is a Querydsl query type for Schema
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSchema extends EntityPathBase<Schema> {

    private static final long serialVersionUID = -393106450L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSchema schema = new QSchema("schema");

    public final QBase _super = new QBase(this);

    public final QPerson contactPerson;

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final NumberPath<Integer> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath registrationCode = createString("registrationCode");

    public final EnumPath<SchemaState> state = createEnum("state", SchemaState.class);

    public QSchema(String variable) {
        this(Schema.class, forVariable(variable), INITS);
    }

    public QSchema(Path<? extends Schema> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSchema(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSchema(PathMetadata metadata, PathInits inits) {
        this(Schema.class, metadata, inits);
    }

    public QSchema(Class<? extends Schema> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.contactPerson = inits.isInitialized("contactPerson") ? new QPerson(forProperty("contactPerson")) : null;
    }

}

