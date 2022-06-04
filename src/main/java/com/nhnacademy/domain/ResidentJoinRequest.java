package com.nhnacademy.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResidentJoinRequest {
    String id;

    String pw;

    String email;
}
