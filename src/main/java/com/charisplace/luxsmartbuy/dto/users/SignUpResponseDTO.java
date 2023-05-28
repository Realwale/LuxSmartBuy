package com.charisplace.luxsmartbuy.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@Getter
@Setter
public class SignUpResponseDTO {

    private String status;

    private String message;
}
