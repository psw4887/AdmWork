package com.nhnacademy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFamilyRelationShip_FamilyRelationShipPK is a Querydsl query type for FamilyRelationShipPK
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFamilyRelationShip_FamilyRelationShipPK extends BeanPath<FamilyRelationShip.FamilyRelationShipPK> {

    private static final long serialVersionUID = -1394313561L;

    public static final QFamilyRelationShip_FamilyRelationShipPK familyRelationShipPK = new QFamilyRelationShip_FamilyRelationShipPK("familyRelationShipPK");

    public final NumberPath<Integer> baseResidentSerialNumber = createNumber("baseResidentSerialNumber", Integer.class);

    public final NumberPath<Integer> familyResidentSerialNumber = createNumber("familyResidentSerialNumber", Integer.class);

    public QFamilyRelationShip_FamilyRelationShipPK(String variable) {
        super(FamilyRelationShip.FamilyRelationShipPK.class, forVariable(variable));
    }

    public QFamilyRelationShip_FamilyRelationShipPK(Path<? extends FamilyRelationShip.FamilyRelationShipPK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFamilyRelationShip_FamilyRelationShipPK(PathMetadata metadata) {
        super(FamilyRelationShip.FamilyRelationShipPK.class, metadata);
    }

}

