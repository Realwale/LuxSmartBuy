package com.charisplace.luxsmartbuy.dto.users;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SignInResponseDTO {

    private String status;

    private String token;
}
