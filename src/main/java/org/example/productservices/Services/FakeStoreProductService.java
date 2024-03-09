package org.example.productservices.Services;

import org.example.productservices.Models.Category;
import org.example.productservices.Models.Product;
import org.example.productservices.dtos.FakeStoreProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDTO[] productDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDTO[].class
        );

        if (productDtos != null) {
            List<Product> products = new ArrayList<>();
            for (FakeStoreProductDTO dto : productDtos) {
                Product product = new Product();
                product.setId(dto.getId());
                product.setTitle(dto.getTitle());
                product.setPrice(dto.getPrice());
                product.setImageUrl(dto.getImage());
                product.setDescription(dto.getDescription());
                Category category = new Category();
                category.setCategoryName(dto.getCategory());
                product.setCategory(category);
                products.add(product);
            }
            return products;
        }
        return null;
    }

    @Override
    public Product getUniqueProduct(Long id) {
        FakeStoreProductDTO FakeStoreProductDTO =  restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDTO.class
        );

        Product product = new Product();
        product.setId(FakeStoreProductDTO.getId());
        product.setTitle(FakeStoreProductDTO.getTitle());
        product.setPrice(FakeStoreProductDTO.getPrice());
        product.setImageUrl(FakeStoreProductDTO.getImage());
        product.setDescription(FakeStoreProductDTO.getDescription());
        product.setCategory(new Category());
        product.getCategory().setCategoryName(FakeStoreProductDTO.getCategory());

        return product;
    }

    @Override
    public Product createProduct(Product product) {
        //update product to fakestoreapi
        //https://fakestoreapi.com/products/id

        FakeStoreProductDTO FakeStoreProductDTO = new FakeStoreProductDTO();
        FakeStoreProductDTO.setId(product.getId());
        FakeStoreProductDTO.setTitle(product.getTitle());
        FakeStoreProductDTO.setPrice(product.getPrice());
        FakeStoreProductDTO.setImage(product.getImageUrl());
        FakeStoreProductDTO.setDescription(product.getDescription());
        FakeStoreProductDTO.setCategory(product.getCategory().getCategoryName());

        restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDTO,
                FakeStoreProductDTO.class
        );
        return null;
    }

    @Override
    public Product updateProduct(Product product) {

        //update product to fakestoreapi

        FakeStoreProductDTO FakeStoreProductDTO = new FakeStoreProductDTO();
        FakeStoreProductDTO.setId(product.getId());
        FakeStoreProductDTO.setTitle(product.getTitle());
        FakeStoreProductDTO.setPrice(product.getPrice());
        FakeStoreProductDTO.setImage(product.getImageUrl());
        FakeStoreProductDTO.setDescription(product.getDescription());
        FakeStoreProductDTO.setCategory(product.getCategory().getCategoryName());

        restTemplate.put(
                "https://fakestoreapi.com/products/" + product.getId(),
                FakeStoreProductDTO
        );

        return product;

    }

    @Override
    public void deleteProduct(Long id) {

        restTemplate.delete("https://fakestoreapi.com/products/" + id);

    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {

        //'https://fakestoreapi.com/products/category/jewelery'
        FakeStoreProductDTO[] productDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + categoryName,
                FakeStoreProductDTO[].class
        );

        if (productDtos != null) {
            List<Product> products = new ArrayList<>();
            for (FakeStoreProductDTO dto : productDtos) {
                Product product = new Product();
                product.setId(dto.getId());
                product.setTitle(dto.getTitle());
                product.setPrice(dto.getPrice());
                product.setImageUrl(dto.getImage());
                product.setDescription(dto.getDescription());
                Category category = new Category();
                category.setCategoryName(dto.getCategory());
                product.setCategory(category);
                products.add(product);
            }
            return products;
        }

        return null;


    }

    @Override
    public List<String> getAllCategories() {
        //https://fakestoreapi.com/products/categories
        String[] categories = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                String[].class
        );

        if (categories != null) {
            List<String> categoryList = new ArrayList<>();
            for (String category : categories) {
                categoryList.add(category);
            }
            return categoryList;
        }
        return null;

    }


}
