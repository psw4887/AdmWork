package com.nhnacademy.domain.vo;

import lombok.Data;

@Data
public class BirthRequest {

    Integer reportResident;

    String relationship;

    String email;

    String phoneNumber;
}
