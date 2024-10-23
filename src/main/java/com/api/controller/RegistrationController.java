package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // it helps to make a API layer
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    // do Dependency Injection by using Service class object and constructor
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @GetMapping //it helps to convert a java object into JSON and @GetMapping means "get() method"
    public ResponseEntity<List<RegistrationDto>> getRegistrations(){ // ResponseEntity helps to write HttpStatus code "HttpStatus.OK" by this we will get 200
        List<RegistrationDto>dtolist=registrationService.getRegistrations();
        System.out.println(dtolist);
        return new ResponseEntity<>(dtolist, HttpStatus.OK);
    }

    // here we also check the validation
    @PostMapping
    public ResponseEntity<?> createRegistration(@Valid @RequestBody RegistrationDto registrationDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError(),HttpStatus.EXPECTATION_FAILED);
        }
        Registration registration=registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(registration,HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(@RequestParam long id){ //@RequestParam can read value from the URL "http://localhost:8080/api/v1/registration?id=1" & initialize this variable "id"
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("SUCCESSFULLY DELETED", HttpStatus.OK);
    }

    @PutMapping("/{id}") // See the total URL "http://localhost:8080/api/v1/registration/1". Here "api/v1/registration" this is our main URI which is defined above before class then we have "/1" this is defined as a "/{id}"
    public ResponseEntity<Registration> updateRegistration(@PathVariable long id, @RequestBody Registration registration){ // our URL is like "http://localhost:8080/api/v1/registration/1" by @PathVariable we store this value 1 into "id". By using @RequestBody store all data in an Entity object ,which data have given into "POSTMAN" body of the Entity class variable
        Registration regi=registrationService.updateRegistration(id,registration);
        return new ResponseEntity<>(regi,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistrationById(@PathVariable long id){
        RegistrationDto dto=registrationService.getDataById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
}
