package com.iyzico.challenge.manager.product;

import com.iyzico.challenge.dto.product.ProductRequestDto;
import com.iyzico.challenge.dto.product.ProductResponseDto;
import com.iyzico.challenge.model.product.Product;
import com.iyzico.challenge.service.ProductService;
import com.iyzico.challenge.util.ModelMapperUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductManager {

    private final ModelMapper modelMapper;
    private final ProductService productService;

    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
        Product product = modelMapper.map(productRequestDto, Product.class);
        Product savedProduct = productService.saveProduct(product);
        return modelMapper.map(savedProduct, ProductResponseDto.class);
    }

    public ProductResponseDto updateProduct(Long productId, ProductRequestDto productRequestDto) {
        Product product = modelMapper.map(productRequestDto, Product.class);
        Product updatedProduct = productService.updateProduct(productId, product);
        return modelMapper.map(updatedProduct, ProductResponseDto.class);
    }

    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ModelMapperUtil.mapList(products, ProductResponseDto.class);
    }
}
