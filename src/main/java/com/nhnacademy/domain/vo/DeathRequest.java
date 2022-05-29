package com.nhnacademy.domain.vo;

import lombok.Data;

@Data
public class DeathRequest {

    Integer reportResident;

    String relationship;

    String email;

    String phoneNumber;
}
