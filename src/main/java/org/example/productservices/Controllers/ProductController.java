package org.example.productservices.Controllers;

import org.example.productservices.Models.Product;
import org.example.productservices.Services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService service)
    {
        this.productService = service;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getUniqueProduct(@PathVariable("id") Long id)
    {
        return productService.getUniqueProduct(id);
    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product)
    {
        return productService.createProduct(product);
    }
    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable("id") Long id)
    {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Long id)
    {
        productService.deleteProduct(id);
    }

    @GetMapping("/products/categories/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable("categoryName") String categoryName)
    {
        return productService.getProductsByCategory(categoryName);
    }

    @GetMapping("/products/categories")
    public List<String> getAllCategories()
    {
        return productService.getAllCategories();
    }
}
