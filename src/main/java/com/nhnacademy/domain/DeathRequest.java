package com.nhnacademy.domain;

import lombok.Data;

@Data
public class DeathRequest {

    Integer reportResident;

    String relationship;

    String email;

    String phoneNumber;
}
