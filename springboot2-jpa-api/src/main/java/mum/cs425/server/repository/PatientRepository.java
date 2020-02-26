package mum.cs425.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mum.cs425.server.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

}
