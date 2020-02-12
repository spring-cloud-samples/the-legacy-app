package com.example.legacyapp.services;

import com.example.legacyapp.TheLegacyApp;
import com.example.legacyapp.dto.Charges;
import com.example.legacyapp.services.CustomerRentalHistoryManager;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

/**
 * Version of the test with stub runner
 *
 * @author Marcin Grzejszczak
 */
@SpringBootTest(classes = TheLegacyApp.class,
		properties = "api.url=http://localhost:9765",
		webEnvironment = NONE)
@AutoConfigureStubRunner(
		ids = "com.example:custom-rental-history:+:stubs:9765"
		// in Intellij you have to use workOffline
		,		stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@Disabled("First install the [custom-rental-history] stubs")
public class CustomerWithStubRunnerExternalContractsTests {

	@Autowired CustomerRentalHistoryManager manager;

	@Test
	public void should_return_charge_collection() {
		Charges charges = manager.listAllCharges("foo");

		BDDAssertions.then(charges.getCharges())
				.hasSize(25);
	}
}