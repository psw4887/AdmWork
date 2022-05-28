package com.nhnacademy.service.impl;

import com.nhnacademy.domain.ResidentModifyRequest;
import com.nhnacademy.domain.ResidentRegisterRequest;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.exception.ResidentNotFoundException;
import com.nhnacademy.repository.ResidentRepository;
import com.nhnacademy.service.ResidentService;
import org.springframework.stereotype.Service;

@Service("residentService")
public class DefResidentService implements ResidentService {
    private final ResidentRepository residentRepository;

    public DefResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public void residentRegister(ResidentRegisterRequest residentRegisterRequest) {
        Resident resident = new Resident();
        resident.setName(residentRegisterRequest.getName());
        resident.setRegistrationNumber(residentRegisterRequest.getRegistrationNumber());
        resident.setGenderCode(residentRegisterRequest.getGenderCode());
        resident.setBirthDate(residentRegisterRequest.getBirth());
        resident.setBirthPlaceCode(residentRegisterRequest.getBirthPlace());
        resident.setRegistrationBaseAddress(residentRegisterRequest.getBaseAddress());
        residentRepository.save(resident);
    }

    @Override
    public void residentModify(int sNum, ResidentModifyRequest residentModifyRequest) {
        Resident resident = residentRepository.findById(sNum).orElseThrow(
            ResidentNotFoundException::new);
        resident.setName(residentModifyRequest.getName());
        resident.setGenderCode(residentModifyRequest.getGenderCode());
    }
}
