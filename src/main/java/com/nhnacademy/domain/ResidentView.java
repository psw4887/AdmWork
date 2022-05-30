package com.nhnacademy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResidentView {
    Integer sNum;

    String name;

    String gender;

    Boolean isBirth;

    Boolean isDeath;
}
