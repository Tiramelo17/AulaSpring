package com.devsuperior.myfirsproject.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.myfirsproject.entities.Category;
import com.devsuperior.myfirsproject.repository.CategoryRepository;
import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryRepository repository;

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Category>> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(repository.findById(id));
	}
		
	@RequestMapping(value="/cadastrarCategoria", method = RequestMethod.POST)
    public ResponseEntity<Category> process(@RequestBody String stringJson) throws Exception {
		Gson gson = new Gson();
	    Category category = gson.fromJson(stringJson, Category.class);
	    return ResponseEntity.ok().body(repository.save(category));

	}
}