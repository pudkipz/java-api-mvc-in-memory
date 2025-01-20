package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/products")
public class ProductController {
    ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductController() {
        this.repository = new ProductRepository();
    }

    @PostMapping
    @ResponseStatus
    public Product create(@RequestBody Product product) {
        Product p = repository.create(product);
        if (p == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product with provided name already exists.");
        }
        return p;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(@RequestParam(name="category", required=false) String category) {
        List<Product> products;
        if (category != null)
            products = this.repository.findAll(category);
        else
            products = this.repository.findAll();
        if (products.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No products of the provided category were found.");
        return products;
    }

    @GetMapping("/{id}")
    @ResponseStatus()
    public Product getOne(@PathVariable(name="id") int id) {
        try {
            return repository.find(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus()
    public Product update(@PathVariable(name="id") int id, @RequestBody Product product) {
        return repository.update(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus()
    public Product delete(@PathVariable(name="id") int id) {
        return repository.delete(id);
    }
}
