package com.sam.healthcare.controller;

import com.sam.healthcare.dto.CreatePatientRequest;
import com.sam.healthcare.dto.PatientResponse;
import com.sam.healthcare.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PatientResponse createPatient(
            @Valid @RequestBody CreatePatientRequest request
    ) {
        return patientService.createPatient(request);
    }

    @GetMapping("/{id}")
    public PatientResponse getPatient(@PathVariable Long id) {
        return patientService.getPatient(id);
    }
}