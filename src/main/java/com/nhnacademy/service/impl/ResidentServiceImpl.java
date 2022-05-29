package com.nhnacademy.service.impl;

import com.nhnacademy.domain.ResidentModifyRequest;
import com.nhnacademy.domain.ResidentRegisterRequest;
import com.nhnacademy.entity.Resident;
import com.nhnacademy.exception.ResidentNotFoundException;
import com.nhnacademy.repository.ResidentRepository;
import com.nhnacademy.service.ResidentService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("residentService")
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;

    public ResidentServiceImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Transactional
    @Override
    public void residentRegister(ResidentRegisterRequest registerRequest) {
        Resident resident = new Resident();
        resident.setName(registerRequest.getName());
        resident.setRegistrationNumber(registerRequest.getRegistrationNumber());
        resident.setGenderCode(registerRequest.getGenderCode());
        resident.setBirthDate(registerRequest.getBirth());
        resident.setBirthPlaceCode(registerRequest.getBirthPlace());
        resident.setRegistrationBaseAddress(registerRequest.getBaseAddress());
        residentRepository.save(resident);
    }

    @Transactional
    @Override
    public void residentModify(int sNum, ResidentModifyRequest modifyRequest) {
        Resident resident = residentRepository.findById(sNum).orElseThrow(
            ResidentNotFoundException::new);
        resident.setName(modifyRequest.getName());
        resident.setGenderCode(modifyRequest.getGenderCode());
    }

    @Transactional
    @Override
    public void residentDelete(int sNum) {
        residentRepository.deleteBySerialNumber(sNum);
    }
}
