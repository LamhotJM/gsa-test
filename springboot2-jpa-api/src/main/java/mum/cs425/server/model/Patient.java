package mum.cs425.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class Patient {

    private long patientId;
    private String patientNumber;
    private String isAnOutPatient;
    private LocalDate dateOfBirth;
    private String fullNames;
    private String contactPhoneNumber;
    private String emailAddress;

    public Patient() {

    }


    public Patient(String fullNames, String contactPhoneNumber, String emailAddress, String patientNumber, String isAnOutPatient, LocalDate dateOfBirth) {
        this.fullNames = fullNames;
        this.contactPhoneNumber = contactPhoneNumber;
        this.emailAddress = emailAddress;
        this.patientNumber = patientNumber;
        this.isAnOutPatient = isAnOutPatient;
        this.dateOfBirth = dateOfBirth;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long id) {
        this.patientId = id;
    }

    @Column(name = "fullNames", nullable = false)
    public String getFullNames() {
        return fullNames;
    }

    public void setFullNames(String fullNames) {
        this.fullNames = fullNames;
    }

    @Column(name = "contactPhoneNumber", nullable = false)
    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    @Column(name = "emailAddress", nullable = false)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


	@Column(name = "patientNumber", nullable = false)
	public String getPatientNumber() {
		return patientNumber;
	}

	public void setPatientNumber(String  patientNumber) {
		this.patientNumber = patientNumber;
	}



	@Column(name = "dateOfBirth", nullable = false)
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate  dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "isAnOutPatient", nullable = false)
	public String getIsAnOutPatient() {
		return isAnOutPatient;
	}

	public void setIsAnOutPatient(String  isAnOutPatient) {
		this.isAnOutPatient = isAnOutPatient;
	}


	@Override
    public String toString() {
        return "Patient [patientId=" + patientId + ", fullNames =" + fullNames + ", contactPhoneNumber=" + contactPhoneNumber + ", emailId=" + emailAddress
                + "]";
    }

}
