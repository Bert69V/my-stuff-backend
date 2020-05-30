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
	private Item item, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12;

	public DevBootstrap(ItemRepo repo) {
		this.repo = repo;
	}

	private void initData()	{
		item = new Item((long) 1, "Hammer", 12, "Keller", "Werkzeug", LocalDate.of(2001,12,15));
		item2 = new Item((long) 2, "Sichel", 7, "Scheune", "Landwirtschaft", LocalDate.of(2013,04,07));
		item3 = new Item((long) 3, "Meißel", 1, "Garage", "Werkzeug", LocalDate.of(2012,01,02));
		item4 = new Item((long) 4, "Gabel", 9, "Küche", "Essen", LocalDate.of(2002,11,07));
		item5 = new Item((long) 5, "Tortenheber", 1, "Küche", "Backen", LocalDate.of(2002,11,07));
		item6 = new Item((long) 6, "Flachzange", 23, "Keller", "Humor", LocalDate.of(2022,11,07));
		item7 = new Item((long) 7, "Schaumschläger", 33, "Dachboden", "Humor", LocalDate.of(2002,11,07));
		item8 = new Item((long) 8, "Luftpumpe", 2, "Garage", "Defekt", LocalDate.of(2002,11,07));
		item9 = new Item((long) 9, "Tischtuchhalter", 4, "Küche", "Feiertage", LocalDate.of(2005,10,06));
		item10 = new Item((long) 10, "Kerzenbieger", 12, "Schublade", "Feiertage", LocalDate.of(2011,12,26));
		item11 = new Item((long) 11, "Rakete", 4, "Dachboden", "Feuerwerk", LocalDate.of(2000,01,01));
		item12 = new Item((long) 12, "Feuerzeug-Adapter", 1, "Garage", "Defekt", LocalDate.of(2002,11,07));
		
		List<Item> newItems = Arrays.asList(item, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12);
		repo.saveAll(newItems);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		initData();
	}
}
