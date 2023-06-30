package com.demo.repository;

import com.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.category.id=?1 AND p.name LIKE ?2 and p.price BETWEEN ?3 AND ?4")
    Page<Product> searchByCategoryNamePrice(String cid, String kw, int minPrice, int maxPrice, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name LIKE ?1 and p.price BETWEEN ?2 AND ?3")
    Page<Product> searchByNamePrice(String kw, int minPrice, int maxPrice, Pageable pageable);
    // Tìm kiếm tất cả sản phẩm
    Page<Product> findAll(Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice")
    Page<Product> findAllByPriceRange(@Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice, Pageable pageable);
    // Tìm kiếm sản phẩm theo phạm vi giá
    @Query("SELECT p FROM Product p WHERE p.price >= :minPrice AND p.price <= :maxPrice")
    Page<Product> findByPriceRange(@Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice, Pageable pageable);

    // Tìm kiếm sản phẩm theo tên
    @Query("SELECT p FROM Product p WHERE p.name LIKE :keyword")
    Page<Product> searchByName(@Param("keyword") String keyword, Pageable pageable);

    // Tìm kiếm sản phẩm theo tên và phạm vi giá
    @Query("SELECT p FROM Product p WHERE p.name LIKE :keyword AND p.price >= :minPrice AND p.price <= :maxPrice")
    Page<Product> searchByNameAndPriceRange(@Param("keyword") String keyword, @Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice, Pageable pageable);
    // Tìm kiếm sản phẩm theo categoryId
    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.id = :categoryId")
    Page<Product> searchByCategoryId(@Param("categoryId") String categoryId, Pageable pageable);
    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.id = :categoryId AND p.price >= :minPrice AND p.price <= :maxPrice")
    Page<Product> searchByCategoryIdAndPriceRange(@Param("categoryId") String categoryId, @Param("minPrice") int minPrice, @Param("maxPrice") int maxPrice, Pageable pageable);
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(:keyword) " +
            "AND p.category.id = :categoryId " +
            "AND p.price >= :minPrice " +
            "AND p.price <= :maxPrice")
    Page<Product> searchByNameCategoryAndPriceRange(
            @Param("keyword") String keyword,
            @Param("categoryId") String categoryId,
            @Param("minPrice") int minPrice,
            @Param("maxPrice") int maxPrice,
            Pageable pageable);
}
