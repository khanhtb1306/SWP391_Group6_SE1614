package com.SE1614.Group6.Model;

import lombok.*;
@AllArgsConstructor
@ToString
@Getter
@Setter
public class RegistrationRequest {
    private final String first_name;
    private final String last_name;
    private final String email;
    private final String password;


}
