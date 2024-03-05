package com.amazonagency.model.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequest {

    @NotBlank(message = "username cannot be empty")
    private String username;

    @NotBlank(message = "email cannot be empty")
    @Email(message = "email should be in format like this: user@example.com")
    private String email;

    @NotBlank
    private String password;

}
