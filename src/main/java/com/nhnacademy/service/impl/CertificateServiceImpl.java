package com.nhnacademy.service.impl;

import com.nhnacademy.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.repository.CertificateIssueRepository;
import com.nhnacademy.service.CertificateService;
import org.springframework.stereotype.Service;

@Service("certificateService")
public class CertificateServiceImpl implements CertificateService {

    private final CertificateIssueRepository cRepository;
    private final BirthDeathReportResidentRepository bRepository;
}
