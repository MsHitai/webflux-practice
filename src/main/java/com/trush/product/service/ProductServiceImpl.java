package com.trush.product.service;

import com.trush.product.dto.ProductDto;
import com.trush.product.mapper.ProductMapper;
import com.trush.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    public Flux<ProductDto> findAll() {
        return productRepository.findAll()
                .map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<ProductDto> findById(String id) {
        return productRepository.findById(id)
                .map(productMapper::toDto);
    }

    @Override
    @Transactional
    public Mono<ProductDto> saveProduct(Mono<ProductDto> dto) {
        return dto.map(productMapper::toEntity)
                .flatMap(productRepository::save)
                .map(productMapper::toDto);
    }

    @Override
    @Transactional
    public Mono<ProductDto> updateProduct(Mono<ProductDto> dto) {
        return dto.map(productMapper::toEntity)
                .flatMap(productRepository::save)
                .map(productMapper::toDto);
    }

    @Override
    @Transactional
    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Flux<ProductDto> findInRange(Double min, Double max) {
        return productRepository.findByPriceBetween(min, max)
                .map(productMapper::toDto);
    }
}
