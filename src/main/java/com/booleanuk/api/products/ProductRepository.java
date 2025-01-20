package com.booleanuk.api.products;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private int idCounter = 1;
    private List<Product> data;

    public ProductRepository() {
        data = new ArrayList<>();
        create("Bagel", "Food", 12);
        create("Apple", "Food", 7);
    }

    public Product create(String name, String category, int price) {
        Product product = new Product(this.idCounter++, name, category, price);
        this.data.add(product);
        return product;
    }

    public Product create(Product product) {
        return this.create(product.getName(), product.getCategory(), product.getPrice());
    }

    public List<Product> findAll() {
        return this.data;
    }

    public Product find(int id) {
        return this.data.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow();
    }
}
