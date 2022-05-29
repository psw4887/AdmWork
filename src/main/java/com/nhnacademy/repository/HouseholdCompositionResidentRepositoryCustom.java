package com.nhnacademy.repository;

import com.nhnacademy.entity.HouseholdCompositionResident;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface HouseholdCompositionResidentRepositoryCustom {

    BooleanExpression checkHouseholderMember(int hNum);
}
