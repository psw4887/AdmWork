package com.nhnacademy.repository;

import com.nhnacademy.entity.HouseholdCompositionResident;
import com.nhnacademy.entity.QHouseholdCompositionResident;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class HouseholdCompositionResidentRepositoryImpl extends QuerydslRepositorySupport
        implements HouseholdCompositionResidentRepositoryCustom  {

    public HouseholdCompositionResidentRepositoryImpl() {
        super(HouseholdCompositionResident.class);
    }

    @Override
    public BooleanExpression checkHouseholderMember(int hNum) {
        QHouseholdCompositionResident householdCompositionResident
                = QHouseholdCompositionResident.householdCompositionResident;

        return from(householdCompositionResident)
                .where(householdCompositionResident.household.serialNumber.eq(hNum))
                .exists();
    }


}
