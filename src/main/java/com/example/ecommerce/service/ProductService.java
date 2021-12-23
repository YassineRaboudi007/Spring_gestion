package com.example.ecommerce.service;

import com.example.ecommerce.dto.AddProductRequestBody;
import com.example.ecommerce.model.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    Product findProductById(Long id);
    Product findProductByName(String name);
    Product addProduct(MultipartFile imageFile,AddProductRequestBody product);
    Product updateProduct(MultipartFile imageFile,Product product);
    void deleteProduct(Long id);
}
