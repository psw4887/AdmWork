package com.nhnacademy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFamilyRelationShip is a Querydsl query type for FamilyRelationShip
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFamilyRelationShip extends EntityPathBase<FamilyRelationShip> {

    private static final long serialVersionUID = 815182910L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFamilyRelationShip familyRelationShip = new QFamilyRelationShip("familyRelationShip");

    public final StringPath familyRelationShipCode = createString("familyRelationShipCode");

    public final QFamilyRelationShip_FamilyRelationShipPK familyRelationShipPK;

    public final QResident resident;

    public QFamilyRelationShip(String variable) {
        this(FamilyRelationShip.class, forVariable(variable), INITS);
    }

    public QFamilyRelationShip(Path<? extends FamilyRelationShip> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFamilyRelationShip(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFamilyRelationShip(PathMetadata metadata, PathInits inits) {
        this(FamilyRelationShip.class, metadata, inits);
    }

    public QFamilyRelationShip(Class<? extends FamilyRelationShip> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.familyRelationShipPK = inits.isInitialized("familyRelationShipPK") ? new QFamilyRelationShip_FamilyRelationShipPK(forProperty("familyRelationShipPK")) : null;
        this.resident = inits.isInitialized("resident") ? new QResident(forProperty("resident")) : null;
    }

}

