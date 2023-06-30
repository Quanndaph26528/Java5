package com.demo.service;

import com.demo.model.Product;
import com.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }
//
//    public Page<Product> searchByCriteria(String categoryId, Integer minPrice, Integer maxPrice, String keyword, Pageable pageable) {
//        Specification<Product> spec = Specification.where(null);
//
//        if (categoryId != null) {
//            spec = spec.and((root, query, builder) -> builder.equal(root.get("category").get("id"), categoryId));
//        }
//
//        if (minPrice != null) {
//            spec = spec.and((root, query, builder) -> builder.greaterThanOrEqualTo(root.get("price"), minPrice));
//        }
//
//        if (maxPrice != null) {
//            spec = spec.and((root, query, builder) -> builder.lessThanOrEqualTo(root.get("price"), maxPrice));
//        }
//
//        if (keyword != null && !keyword.isEmpty()) {
//            String keywordLike = "%" + keyword.toLowerCase() + "%";
//            spec = spec.and((root, query, builder) ->
//                    builder.or(
//                            builder.like(builder.lower(root.get("name")), keywordLike),
//                            builder.like(builder.lower(root.get("category").get("name")), keywordLike)
//                    )
//            );
//        }
//
//        return productRepository.findAll(spec, pageable);
//    }
}
