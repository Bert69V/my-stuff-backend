package de.telekom.sea.mystuff.backend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URISyntaxException;
import java.time.LocalDate;

import de.telekom.sea.mystuff.backend.entity.Item;
import de.telekom.sea.mystuff.backend.repository.ItemRepo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ItemRestControllerTest {

	private static final String BASE_PATH = "/api/v1/items";

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ItemRepo repo;

	@BeforeEach
	void clearData() {
		repo.deleteAll();
	}

	@Test
	void shouldBeAbleToUploadAnItem() {
		Item testfall = createTinOpener();

		ResponseEntity<Item> response = restTemplate.postForEntity(BASE_PATH, testfall, Item.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody().getLocation().equals(testfall.getLocation()));

	}

	@Test
	void shouldReadAllItems() {
		createTinOpener();
		createTinOpener();
		createTinOpener();
		createTinOpener();
				
		ResponseEntity<Item []> anfrage = restTemplate.getForEntity(BASE_PATH, Item [].class); 
		assertThat(anfrage.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(anfrage.getBody().length == 4);
	}

	@Test
	void shouldFindOneItem() {

	}

	@Test
	void shouldFindNoItemForUnknownId() throws URISyntaxException {

	}

	@Test
	void shouldBeAbleToDeleteAnItem() throws URISyntaxException {

	}

	@Test
	void shouldNotBeAbleToDeleteAnItemWithUnknownId() throws URISyntaxException {
	}

	@Test
	void shouldBeAbleToReplaceAnItem() throws URISyntaxException {

	}

	@Test
	void shouldNotBeAbleToReplaceAnItemWithUnknownId() throws URISyntaxException {

	}

	private ResponseEntity<Item> givenAnInsertedItem() {
		Item example = createTinOpener();
		return restTemplate.postForEntity(BASE_PATH, example, Item.class);
	}

	private Item createTinOpener() {
		Item example = new Item();
		example.setName("Tin-Opener");
		example.setAmount(3);
		example.setLocation("Kitchen");
		example.setDescription("Tool");
		example.setLastUsed((LocalDate.of(2012, 06, 11)));
		return example;
	}

}
