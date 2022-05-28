package com.nhnacademy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBirthDeathReportResident is a Querydsl query type for BirthDeathReportResident
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBirthDeathReportResident extends EntityPathBase<BirthDeathReportResident> {

    private static final long serialVersionUID = 1667832283L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBirthDeathReportResident birthDeathReportResident = new QBirthDeathReportResident("birthDeathReportResident");

    public final DateTimePath<java.util.Date> birthDeathReportDate = createDateTime("birthDeathReportDate", java.util.Date.class);

    public final QBirthDeathReportResident_BirthDeathReportResidentPK birthDeathReportResidentPK;

    public final StringPath birthReportQualificationsCode = createString("birthReportQualificationsCode");

    public final StringPath deathReportQualificationsCode = createString("deathReportQualificationsCode");

    public final StringPath email = createString("email");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final QResident resident;

    public QBirthDeathReportResident(String variable) {
        this(BirthDeathReportResident.class, forVariable(variable), INITS);
    }

    public QBirthDeathReportResident(Path<? extends BirthDeathReportResident> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBirthDeathReportResident(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBirthDeathReportResident(PathMetadata metadata, PathInits inits) {
        this(BirthDeathReportResident.class, metadata, inits);
    }

    public QBirthDeathReportResident(Class<? extends BirthDeathReportResident> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.birthDeathReportResidentPK = inits.isInitialized("birthDeathReportResidentPK") ? new QBirthDeathReportResident_BirthDeathReportResidentPK(forProperty("birthDeathReportResidentPK")) : null;
        this.resident = inits.isInitialized("resident") ? new QResident(forProperty("resident")) : null;
    }

}

