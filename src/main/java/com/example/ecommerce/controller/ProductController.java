package com.example.ecommerce.controller;


import com.example.ecommerce.dto.AddProductRequestBody;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getUser(@PathVariable("id") Long id){
        Product product = productService.findProductById(id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Product> addUser(@RequestParam("imageFile")MultipartFile imageFile, AddProductRequestBody productRequestBody){
        Product product = productService.addProduct(imageFile,productRequestBody);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateUser(@RequestParam("imageFile")MultipartFile imageFile,@RequestBody Product product){
        Product user = productService.updateProduct(imageFile,product);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<String>("Product deleted succesfully", HttpStatus.OK);
    }
}
