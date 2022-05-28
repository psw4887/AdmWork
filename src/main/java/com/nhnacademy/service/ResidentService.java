package com.nhnacademy.service;

import com.nhnacademy.domain.ResidentModifyRequest;
import com.nhnacademy.domain.ResidentRegisterRequest;

public interface ResidentService {

    void residentRegister(ResidentRegisterRequest residentRegisterRequest);

    void residentModify(int sNum, ResidentModifyRequest residentModifyRequest);
}
