package com.api.service;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFound;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.api.A.mapToDto;

@Service
public class RegistrationService {

    // do Dependency Injection by using crteate a variable of RegistrationRepository and its Constructor
    private RegistrationRepository registrationRepository;
    private ModelMapper modelmapper; // create constructor for DEPENDENCY INJECTION

    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelmapper) {
        this.registrationRepository = registrationRepository;
        this.modelmapper = modelmapper;
    }
    // ========== copy the data from database and store into list.============
    public List<RegistrationDto> getRegistrations(){
        // fetch the data from the db and store into a "list of object(Registration)"
        List<Registration> registrations=registrationRepository.findAll();
        // now copy the data from "registrations" and store into "RegistrationDto object type list" & this "mapToDto()" method will be created into "A.java" class
        List<RegistrationDto>dtolist=registrations.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return dtolist;

    }

    // Dto means Data Transfer Object
    public Registration createRegistration(RegistrationDto registrationDto) {
       Registration registration = new Registration();
       modelmapper.map(registrationDto,registration);
       //registration.setName(registrationDto.getName());
       //registration.setMobile(registrationDto.getMobile());
       //registration.setEmail(registrationDto.getEmail());
       Registration savedEntity=registrationRepository.save(registration);
       return savedEntity;
    }





    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }

    public Registration updateRegistration(long id, Registration registration) {
        // for doing update at first we have to take the all data of a particular id and store into a Entity object
        Registration regobj=registrationRepository.findById(id).get();
        // now get the data from the RequestBody variables and store into "regobj" variables
        regobj.setId(id);
        regobj.setName(registration.getName());
        regobj.setEmail(registration.getEmail());
        regobj.setMobile(registration.getMobile());
        // now save this Entity class object into DB
        Registration savedEntity=registrationRepository.save(regobj);
        return savedEntity;
    }

    public RegistrationDto getDataById(long id){
        Registration registration=registrationRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Record not Found"));
        // convert registration into RegistrationDto object
        return mapToDto(registration);

    }
}
