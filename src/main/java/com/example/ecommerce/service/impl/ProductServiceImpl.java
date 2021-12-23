package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.AddProductRequestBody;
import com.example.ecommerce.exception.MyException;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.utils.FileUploadUtils;
import com.example.ecommerce.utils.ObjectUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private FileUploadUtils fileUploadUtils;

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new MyException("Product not found"));
    }

    @Override
    public Product findProductByName(String name) {
        return productRepository.findProductByName(name).orElseThrow(()-> new MyException("Product not found"));
    }

    @Override
    public Product addProduct(MultipartFile imageFile, AddProductRequestBody product) {
        if (ObjectUtil.isAnyFieldEmpty(product)){
            throw new MyException("Product Field is empty");
        }
        Product newProduct = new Product();
        String fileName = imageFile.getOriginalFilename()+java.time.LocalDateTime.now().toLocalTime().getNano();
        fileUploadUtils.saveFile(imageFile,fileName);
        newProduct.setImage(fileName);
        newProduct.setPrice(product.getPrice());
        newProduct.setName(product.getName());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setCreatedAt(Instant.now());
        newProduct.setUpdatedAt(null);
        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(MultipartFile imageFile,Product product) {
        if (ObjectUtil.isAnyFieldEmpty(product)){
            throw new MyException("Product Field is empty");
        }
        Product thisProd = findProductById(product.getId());
        if (imageFile.getOriginalFilename() != product.getImage()){
            fileUploadUtils.deleteFile(product.getImage());
        }
        fileUploadUtils.saveFile(imageFile,product.getImage());
        thisProd.setImage(product.getImage());
        thisProd.setPrice(product.getPrice());
        thisProd.setName(product.getName());
        thisProd.setQuantity(product.getQuantity());
        thisProd.setId(product.getId());
        thisProd.setCreatedAt(product.getCreatedAt());
        thisProd.setUpdatedAt(Instant.now());
        return productRepository.save(thisProd);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = findProductById(id);
        fileUploadUtils.deleteFile(product.getImage());
        productRepository.delete(product);
    }
}
