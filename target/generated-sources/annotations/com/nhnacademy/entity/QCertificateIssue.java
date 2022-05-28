package com.nhnacademy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCertificateIssue is a Querydsl query type for CertificateIssue
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCertificateIssue extends EntityPathBase<CertificateIssue> {

    private static final long serialVersionUID = -1405903260L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCertificateIssue certificateIssue = new QCertificateIssue("certificateIssue");

    public final NumberPath<Long> confirmationNumber = createNumber("confirmationNumber", Long.class);

    public final DateTimePath<java.util.Date> issueDate = createDateTime("issueDate", java.util.Date.class);

    public final QResident resident;

    public final StringPath typeCode = createString("typeCode");

    public QCertificateIssue(String variable) {
        this(CertificateIssue.class, forVariable(variable), INITS);
    }

    public QCertificateIssue(Path<? extends CertificateIssue> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCertificateIssue(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCertificateIssue(PathMetadata metadata, PathInits inits) {
        this(CertificateIssue.class, metadata, inits);
    }

    public QCertificateIssue(Class<? extends CertificateIssue> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resident = inits.isInitialized("resident") ? new QResident(forProperty("resident")) : null;
    }

}

