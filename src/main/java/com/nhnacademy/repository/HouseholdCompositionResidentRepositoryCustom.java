package com.nhnacademy.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface HouseholdCompositionResidentRepositoryCustom {

    BooleanExpression checkHouseholderMember(int hNum);
}
