package com.nhnacademy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHouseholdMovementAddress is a Querydsl query type for HouseholdMovementAddress
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHouseholdMovementAddress extends EntityPathBase<HouseholdMovementAddress> {

    private static final long serialVersionUID = -1524428472L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHouseholdMovementAddress householdMovementAddress = new QHouseholdMovementAddress("householdMovementAddress");

    public final QHousehold household;

    public final QHouseholdMovementAddress_HouseholdMovementAddressPK householdMovementAddressPK;

    public final StringPath houseMovementAddress = createString("houseMovementAddress");

    public final StringPath lastAddressYN = createString("lastAddressYN");

    public QHouseholdMovementAddress(String variable) {
        this(HouseholdMovementAddress.class, forVariable(variable), INITS);
    }

    public QHouseholdMovementAddress(Path<? extends HouseholdMovementAddress> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHouseholdMovementAddress(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHouseholdMovementAddress(PathMetadata metadata, PathInits inits) {
        this(HouseholdMovementAddress.class, metadata, inits);
    }

    public QHouseholdMovementAddress(Class<? extends HouseholdMovementAddress> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.household = inits.isInitialized("household") ? new QHousehold(forProperty("household"), inits.get("household")) : null;
        this.householdMovementAddressPK = inits.isInitialized("householdMovementAddressPK") ? new QHouseholdMovementAddress_HouseholdMovementAddressPK(forProperty("householdMovementAddressPK")) : null;
    }

}

