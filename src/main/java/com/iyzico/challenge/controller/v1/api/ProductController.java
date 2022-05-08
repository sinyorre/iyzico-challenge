package com.iyzico.challenge.controller.v1.api;

import com.iyzico.challenge.dto.product.ProductRequestDto;
import com.iyzico.challenge.dto.product.ProductResponseDto;
import com.iyzico.challenge.manager.product.ProductManager;
import com.iyzico.challenge.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductManager productManager;

    @PostMapping
    public ResponseEntity<ProductResponseDto> saveProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseDto = productManager.saveProduct(productRequestDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Long> deleteProduct(@PathVariable("productId") long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>(productId, HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDto> updateProduct(
            @PathVariable("productId") long productId,
            @Valid @RequestBody ProductRequestDto productRequestDto
    ) {
        ProductResponseDto productResponseDto = productManager.updateProduct(productId, productRequestDto);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return new ResponseEntity<>(productManager.getAllProducts(), HttpStatus.OK);
    }
}
