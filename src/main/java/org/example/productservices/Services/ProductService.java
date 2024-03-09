package org.example.productservices.Services;

import org.example.productservices.Models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getUniqueProduct(Long id);

    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long id);
    List<Product> getProductsByCategory(String categoryName);
    List<String>getAllCategories();
}
