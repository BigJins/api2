package org.zerock.api2.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.api2.product.domain.Product;
import org.zerock.api2.product.domain.Review;

public interface ReviewRepository  extends JpaRepository<Review, Long> {

    Page<Review> findByProduct(Product product, Pageable pageable);

    @Query("select r.rno, ri.filename, ri.ord from Review r left join r.images ri where r.product.pno = :pno and (ri is null or ri.ord = 0)") // 상품하나 리뷰이미지하나
    Page<Object[]> listByProductQuery(@Param("pno") Long pno, Pageable pageable);

}
