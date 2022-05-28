package com.nhnacademy.service;

import com.nhnacademy.domain.ResidentModifyRequest;
import com.nhnacademy.domain.ResidentRegisterRequest;
import com.nhnacademy.entity.Resident;


public interface ResidentService {

    Resident getResi(int sNum);

    void residentRegister(ResidentRegisterRequest residentRegisterRequest);

    void residentModify(int sNum, ResidentModifyRequest residentModifyRequest);

    void residentDelete(int sNum);
}
