package mum.cs425.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import mum.cs425.server.model.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllPatients() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/patients",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetPatientById() {
		Patient patient = restTemplate.getForObject(getRootUrl() + "/patients/1", Patient.class);
		System.out.println(patient.getFullNames());
		assertNotNull(patient);
	}

	@Test
	public void testCreatePatient() {
		Patient patient = new Patient();
		patient.setEmailAddress("lamhot@gmail.com");
		patient.setFullNames("Lamhot Siagian");
		patient.setContactPhoneNumber("+62 82369603711");
		patient.setIsAnOutPatient("yes");
		patient.setDateOfBirth(LocalDate.of(2017,11,10));
		patient.setPatientNumber("P10000001");

		ResponseEntity<Patient> postResponse = restTemplate.postForEntity(getRootUrl() + "/patients", patient, Patient.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdatePatient() {
		int id = 1;
		Patient patient = restTemplate.getForObject(getRootUrl() + "/patients/" + id, Patient.class);
		patient.setFullNames("admin1");
		patient.setContactPhoneNumber("admin2");

		restTemplate.put(getRootUrl() + "/patients/" + id, patient);

		Patient updatedPatient = restTemplate.getForObject(getRootUrl() + "/patients/" + id, Patient.class);
		assertNotNull(updatedPatient);
	}

	@Test
	public void testDeletePatient() {
		int id = 2;
		Patient patient = restTemplate.getForObject(getRootUrl() + "/patients/" + id, Patient.class);
		assertNotNull(patient);

		restTemplate.delete(getRootUrl() + "/patients/" + id);

		try {
			patient = restTemplate.getForObject(getRootUrl() + "/patients/" + id, Patient.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
