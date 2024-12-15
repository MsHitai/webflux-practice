package com.trush.product.service;

import com.trush.product.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<ProductDto> findAll();

    Mono<ProductDto> findById(String id);

    Mono<ProductDto> saveProduct(Mono<ProductDto> dto);

    Mono<ProductDto> updateProduct(Mono<ProductDto> dto);

    Mono<Void> deleteProduct(String id);

    Flux<ProductDto> findInRange(Double min, Double max);
}
