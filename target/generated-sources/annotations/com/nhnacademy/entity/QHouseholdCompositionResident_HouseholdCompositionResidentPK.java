package com.nhnacademy.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHouseholdCompositionResident_HouseholdCompositionResidentPK is a Querydsl query type for HouseholdCompositionResidentPK
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QHouseholdCompositionResident_HouseholdCompositionResidentPK extends BeanPath<HouseholdCompositionResident.HouseholdCompositionResidentPK> {

    private static final long serialVersionUID = 2015460743L;

    public static final QHouseholdCompositionResident_HouseholdCompositionResidentPK householdCompositionResidentPK = new QHouseholdCompositionResident_HouseholdCompositionResidentPK("householdCompositionResidentPK");

    public final NumberPath<Integer> householdSerialNumber = createNumber("householdSerialNumber", Integer.class);

    public final NumberPath<Integer> residentSerialNumber = createNumber("residentSerialNumber", Integer.class);

    public QHouseholdCompositionResident_HouseholdCompositionResidentPK(String variable) {
        super(HouseholdCompositionResident.HouseholdCompositionResidentPK.class, forVariable(variable));
    }

    public QHouseholdCompositionResident_HouseholdCompositionResidentPK(Path<? extends HouseholdCompositionResident.HouseholdCompositionResidentPK> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHouseholdCompositionResident_HouseholdCompositionResidentPK(PathMetadata metadata) {
        super(HouseholdCompositionResident.HouseholdCompositionResidentPK.class, metadata);
    }

}

