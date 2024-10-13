package org.zerock.api2.product.repository.search;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.api2.common.dto.PageRequestDTO;
import org.zerock.api2.common.dto.PageResponseDTO;
import org.zerock.api2.product.domain.Product;
import org.zerock.api2.product.domain.ProductStatus;
import org.zerock.api2.product.dto.ProductListDTO;
import org.zerock.api2.product.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Log4j2
public class ProductSearchImplTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void getProductsList() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
        productRepository.getProductsList(pageable);
    }

    @Test
    public void searchProductsList() {
        //given
        Pageable pageable = PageRequest.of(0, 10, Sort.by("pno").descending());
        String keyword = "Product";
        ProductStatus productStatus = ProductStatus.SALE;
        //when
        Page<Product> result = productRepository.searchProductsList(keyword, productStatus, pageable);
        //then
        // 검증 (결과가 null이 아니고, 페이지가 제대로 반환되는지 확인)
        assertNotNull(result);
        assertTrue(result.getTotalElements() > 0);
        result.getContent().forEach(product -> {
            assertTrue(product.getPname().contains(keyword));
            assertEquals(ProductStatus.SALE, product.getStatus());
        });

    }

    @Test
    public void testListWithReplyCount() {
        //given
        Pageable pageable = PageRequest.of(0, 10,Sort.by("pno").descending());
        productRepository.listWithReplyCount(pageable);
        //when

        //then
    }

    @Test
    public void testRead() {
        log.info("00000000");
        log.info(productRepository.read(15L));
    }
    @Test
    public void testDTOList() {
        PageRequestDTO requestDTO = PageRequestDTO.builder().build();
        PageResponseDTO<ProductListDTO> result
                = productRepository.list(requestDTO);
        log.info(result);
    }


}