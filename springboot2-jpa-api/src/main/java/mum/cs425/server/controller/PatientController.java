package mum.cs425.server.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mum.cs425.server.exception.ResourceNotFoundException;
import mum.cs425.server.model.Patient;
import mum.cs425.server.repository.PatientRepository;

@RestController
@RequestMapping("/api/v1")
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;

	@GetMapping("/patients")
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable(value = "id") Long patientId)
			throws ResourceNotFoundException {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
		return ResponseEntity.ok().body(patient);
	}

	@PostMapping("/patients")
	public Patient createPatient(@Valid @RequestBody Patient patient) {
		return patientRepository.save(patient);
	}

	@PutMapping("/patients/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable(value = "id") Long patientId,
												  @Valid @RequestBody Patient patientDetails) throws ResourceNotFoundException {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));

		patient.setEmailAddress(patientDetails.getEmailAddress());
		patient.setContactPhoneNumber(patientDetails.getContactPhoneNumber());
		patient.setFullNames(patientDetails.getFullNames());
		patient.setPatientNumber(patientDetails.getPatientNumber());
		patient.setDateOfBirth(patientDetails.getDateOfBirth());
		patient.setIsAnOutPatient(patientDetails.getIsAnOutPatient());
		final Patient updatedPatient = patientRepository.save(patient);
		return ResponseEntity.ok(updatedPatient);
	}

	@DeleteMapping("/patients/{id}")
	public Map<String, Boolean> deletePatient(@PathVariable(value = "id") Long patientId)
			throws ResourceNotFoundException {
		Patient patient = patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));

		patientRepository.delete(patient);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
