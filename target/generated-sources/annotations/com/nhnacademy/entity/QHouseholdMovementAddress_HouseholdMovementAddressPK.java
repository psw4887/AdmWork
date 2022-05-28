package com.nhnacademy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHouseholdMovementAddress_HouseholdMovementAddressPK is a Querydsl query type for HouseholdMovementAddressPK
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QHouseholdMovementAddress_HouseholdMovementAddressPK extends BeanPath<HouseholdMovementAddress.HouseholdMovementAddressPK> {

    private static final long serialVersionUID = 818764647L;

    public static final QHouseholdMovementAddress_HouseholdMovementAddressPK householdMovementAddressPK = new QHouseholdMovementAddress_HouseholdMovementAddressPK("householdMovementAddressPK");

    public final NumberPath<Integer> householdSerialNumber = createNumber("householdSerialNumber", Integer.class);

    public final DateTimePath<java.util.Date> movementReportDate = createDateTime("movementReportDate", java.util.Date.class);

    public QHouseholdMovementAddress_HouseholdMovementAddressPK(String variable) {
        super(HouseholdMovementAddress.HouseholdMovementAddressPK.class, forVariable(variable));
    }

    public QHouseholdMovementAddress_HouseholdMovementAddressPK(Path<? extends HouseholdMovementAddress.HouseholdMovementAddressPK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHouseholdMovementAddress_HouseholdMovementAddressPK(PathMetadata metadata) {
        super(HouseholdMovementAddress.HouseholdMovementAddressPK.class, metadata);
    }

}

