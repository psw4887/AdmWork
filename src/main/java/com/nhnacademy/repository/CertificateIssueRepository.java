package com.nhnacademy.repository;

import com.nhnacademy.entity.CertificateIssue;
import com.nhnacademy.entity.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {

    Page<CertificateIssue> getAllByResident(Resident resident, Pageable pageable);
}
