package com.api.payload;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
    @NotNull
    @Size(max=2, message = "you have to put minimum 2 letters")
    private String name;
    @Email
    private String email;

    @Size(max=10, min=10, message = "put here mobile number")
    private String mobile;

}
