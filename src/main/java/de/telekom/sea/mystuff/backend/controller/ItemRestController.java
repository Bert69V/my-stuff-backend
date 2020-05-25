package de.telekom.sea.mystuff.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import de.telekom.sea.mystuff.backend.entity.Item;
import de.telekom.sea.mystuff.backend.repository.ItemRepo;;

@RestController
@RequestMapping("/api/v1/items")	
public class ItemRestController {

private final ItemRepo repo;

		@Autowired
		public ItemRestController(ItemRepo repo) {
			super();
			this.repo = repo;
		}

		@GetMapping
		@ResponseStatus(value = HttpStatus.OK)
		public List<Item> getAll() {
			return this.repo.findAll();
		}

		@GetMapping("{/id}")
		@ResponseStatus(value = HttpStatus.OK)
		public Item getById(@PathVariable("id") Long id) {
			return this.repo.findById(id).orElseThrow(() -> {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			});
		}
		
		
		@PostMapping
		@ResponseStatus(value = HttpStatus.CREATED)
		public Item saveItem(@RequestBody Item newImage) {
			return this.repo.save(newImage);
		}

		

		@PutMapping("{id}")
		@ResponseStatus(value = HttpStatus.OK)
		 public Item updateItem (@RequestBody Item newItem, @PathVariable Long id) {
		  		
		Item item = repo.findById(id).get(); 
		item.setName(newItem.getName());
		item.setAmount(newItem.getAmount());
		item.setLocation(newItem.getLocation());
		item.setDescription(newItem.getDescription());
		item.setLastUsed(newItem.getLastUsed());
		repo.save(item);
					
		return item; 			
		    			
		}		    				

		@DeleteMapping("{/id}")
		@ResponseStatus(value = HttpStatus.NO_CONTENT)
		  public void deleteItem(@PathVariable Long id) {
		    repo.deleteById(id);
		  }

	}
	
	

