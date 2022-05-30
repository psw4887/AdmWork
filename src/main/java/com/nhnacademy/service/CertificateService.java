package com.nhnacademy.service;

import com.nhnacademy.domain.dto.CertificateDTO;
import com.nhnacademy.domain.dto.family.FamilyCertificateDTO;

import java.util.List;

public interface CertificateService {

    List<CertificateDTO> getCertificateList(int sNUm);

    FamilyCertificateDTO getFamilyCertificate(int sNum);
}
