package com.booleanuk.api.products;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
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
        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus()
    public Product getOne(@PathVariable(name="id") int id, @RequestBody Product product) {
        return null;
    }

    @PutMapping("/{id}")
    @ResponseStatus()
    public Product update(@PathVariable(name="id") int id, @RequestBody Product product) {
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus()
    public Product delete(@PathVariable(name="id") int id) {
        return null;
    }

}
