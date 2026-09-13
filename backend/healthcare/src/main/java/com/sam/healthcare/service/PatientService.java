package com.sam.healthcare.service;

import com.sam.healthcare.dto.CreatePatientRequest;
import com.sam.healthcare.dto.PatientResponse;
import com.sam.healthcare.entity.Patient;
import com.sam.healthcare.exception.DuplicateEmailException;
import com.sam.healthcare.exception.PatientNotFoundException;
import com.sam.healthcare.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional
    public PatientResponse createPatient(CreatePatientRequest request) {

        if (patientRepository.existsByEmail(request.email())) {
            throw new DuplicateEmailException(request.email());
        }

        Patient patient = new Patient();

        patient.setFirstName(request.firstName());
        patient.setLastName(request.lastName());
        patient.setEmail(request.email());
        patient.setPhone(request.phone());

        Patient savedPatient = patientRepository.save(patient);

        return toResponse(savedPatient);
    }

    @Transactional(readOnly = true)
    public PatientResponse getPatient(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

        return toResponse(patient);
    }

    private PatientResponse toResponse(Patient patient) {
        return new PatientResponse(
                patient.getId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getCreatedAt()
        );
    }
}