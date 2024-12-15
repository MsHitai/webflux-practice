package com.trush.product.controller;

import com.trush.product.dto.ProductDto;
import com.trush.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProductDto>> findById(@PathVariable(name = "id") String id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/range")
    public Flux<ProductDto> findInRange(@RequestParam(name = "min", defaultValue = "0") Double min,
                                        @RequestParam(name = "max", defaultValue = "50") Double max) {
        return productService.findInRange(min, max);
    }

    @PostMapping
    public Mono<ProductDto> saveProduct(@Valid @RequestBody Mono<ProductDto> dto) {
        return productService.saveProduct(dto);
    }

    @PutMapping
    public Mono<ProductDto> updateProduct(@Valid @RequestBody Mono<ProductDto> dto) {
        return productService.updateProduct(dto);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteProductById(@PathVariable(name = "id") String id) {
        return productService.deleteProduct(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))
                        .defaultIfEmpty(ResponseEntity.notFound().build()));
    }
}
