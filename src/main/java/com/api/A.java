package com.api;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;

public class A {
    public static void main(String[] args){

    }
    public static RegistrationDto mapToDto(Registration registration){
        RegistrationDto registrationDto= new RegistrationDto();
        registrationDto.setName(registration.getName());
        registrationDto.setMobile(registration.getMobile());
        registrationDto.setEmail(registration.getEmail());
        return registrationDto;
    }
}
