package com.tmo.phones;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PhonesApiApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Autowired
	PhonesRepository phonesRepository;

	@BeforeEach
	void setUp() {
		phonesRepository.save(new Phone("iphone", "11 max", "ios"));
	}

	@AfterEach
	void tearDown() {
		phonesRepository.deleteAll();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void getPhoneDetails_returnsPhoneDetails() {

		// access and api and receive details about a phone
		ResponseEntity<Phone> response = restTemplate.getForEntity("/api/phones/iphone", Phone.class);

		// from AssertJ
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getMake()).isEqualTo("iphone");
		assertThat(response.getBody().getModel()).isEqualTo("11 max");
	}
}
