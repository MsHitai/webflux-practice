package com.trush.product.mapper;

import com.trush.product.dto.ProductDto;
import com.trush.product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProductMapper {

    Product toEntity(ProductDto dto);

    ProductDto toDto(Product entity);
}
