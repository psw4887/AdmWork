package com.nhnacademy.service;

import com.nhnacademy.domain.dto.birth.BirthDTO;
import com.nhnacademy.domain.dto.death.DeathDTO;
import com.nhnacademy.domain.dto.birth.family.FamilyCertificateDTO;
import com.nhnacademy.domain.dto.registration.RegistrationDTO;
import com.nhnacademy.entity.CertificateIssue;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CertificateService {

    List<CertificateIssue> getCertificateList(int sNUm, Pageable pageable);

    FamilyCertificateDTO getFamilyCertificate(int sNum);

    RegistrationDTO getRegistrationCertificate(int sNum);

    BirthDTO getBirthCertificate(int sNum);

    DeathDTO getDeathCertificate(int sNum);

    void addCertificate(CertificateIssue cert);
}
