package com.devsuperior.myfirsproject.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.myfirsproject.entities.Category;
import com.devsuperior.myfirsproject.entities.Product;
import com.devsuperior.myfirsproject.repository.ProductRepository;
import com.google.gson.Gson;

@RestController
@RequestMapping (value="/product")
public class ProductResources {

	@Autowired
	private ProductRepository repository;

	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Product>> findById(@PathVariable Long id) {
		// posso colocar  o tipo do retorno assim, ou apensa colocar um .get apois o findbyid para pegar o objeto dentro do Optional 
		return ResponseEntity.ok().body(repository.findById(id));
	}
	
	
	@RequestMapping(value="/cadastrarProduto", method = RequestMethod.POST)
    public ResponseEntity<Product> process(@RequestBody String stringJson) throws Exception {
		Gson gson = new Gson();
		Product product = gson.fromJson(stringJson, Product.class);
	    return ResponseEntity.ok().body(repository.save(product));

	}

}
