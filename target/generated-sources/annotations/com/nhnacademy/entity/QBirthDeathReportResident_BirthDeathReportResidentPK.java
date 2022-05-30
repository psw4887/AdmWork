package com.nhnacademy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBirthDeathReportResident_BirthDeathReportResidentPK is a Querydsl query type for BirthDeathReportResidentPK
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBirthDeathReportResident_BirthDeathReportResidentPK extends BeanPath<BirthDeathReportResident.BirthDeathReportResidentPK> {

    private static final long serialVersionUID = -873480505L;

    public static final QBirthDeathReportResident_BirthDeathReportResidentPK birthDeathReportResidentPK = new QBirthDeathReportResident_BirthDeathReportResidentPK("birthDeathReportResidentPK");

    public final StringPath birthDeathTypeCode = createString("birthDeathTypeCode");

    public final NumberPath<Integer> reportResidentSerialNumber = createNumber("reportResidentSerialNumber", Integer.class);

    public final NumberPath<Integer> residentSerialNumber = createNumber("residentSerialNumber", Integer.class);

    public QBirthDeathReportResident_BirthDeathReportResidentPK(String variable) {
        super(BirthDeathReportResident.BirthDeathReportResidentPK.class, forVariable(variable));
    }

    public QBirthDeathReportResident_BirthDeathReportResidentPK(Path<? extends BirthDeathReportResident.BirthDeathReportResidentPK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBirthDeathReportResident_BirthDeathReportResidentPK(PathMetadata metadata) {
        super(BirthDeathReportResident.BirthDeathReportResidentPK.class, metadata);
    }

}

