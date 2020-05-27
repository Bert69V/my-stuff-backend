package de.telekom.sea.mystuff.backend.devbootstrap;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import de.telekom.sea.mystuff.backend.entity.Item;
import de.telekom.sea.mystuff.backend.repository.ItemRepo;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private ItemRepo repo;
	private Item item, item2, item3, item4;

	public DevBootstrap(ItemRepo repo) {
		this.repo = repo;
	}

	private void initData() {
		item = new Item((long) 1, "Hammer", 12, "Keller4", "Werkzeug", LocalDate.of(2001,12,15));
		item2 = new Item((long) 2, "Sichel", 7, "Keller3", "Landwirtschaft", LocalDate.of(2013,04,07));
		item3 = new Item((long) 3, "Meißel", 1, "Keller2", "Werkzeug", LocalDate.of(2012,01,02));
		item4 = new Item((long) 4, "Gabel", 9, "Garage", "Bestcek", LocalDate.of(2002,11,07));

		List<Item> newItems = Arrays.asList(item, item2, item3, item4);
		repo.saveAll(newItems);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initData();
	}
}
