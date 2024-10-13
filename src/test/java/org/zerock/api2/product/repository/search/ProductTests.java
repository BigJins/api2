package org.zerock.api2.product.repository.search;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.api2.product.domain.Product;
import org.zerock.api2.product.repository.ProductRepository;

import java.util.HashSet;
import java.util.Optional;

@DataJpaTest
@Log4j2
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    @Commit
    public void testInsert() {

        HashSet<String> tagSet = new HashSet<>();
        tagSet.add("test1");
        tagSet.add("test2");
        tagSet.add("test3");

        Product product = Product.builder()
                .pname("Test Product")
                .price(3000)
                .build();

//        product.addTag("AAA");
//        product.addTag("BBB");
//        product.addTag("CCC");

        productRepository.save(product);
    }

    @Test
    public void testReadTag() {

        Optional<Product> result = productRepository.findById(1L);

        Product product = result.get();

        log.info(product);
    }

    @Test
    public void testRead() {

        Optional<Product> result = productRepository.read2(1L);

        Product product = result.get();

        log.info(product);
        log.info("---------------------------");
        log.info(product.getTags());
    }

    @Test
    @Transactional
    @Commit
    public void testUpdate() {

        Optional<Product> result = productRepository.read2(1L);

        Product product = result.get();

        product.clearTags();
        product.addTag("111");
        product.addTag("222");
        product.addTag("333");

    }

    @Test
    public void testDTOList() {
        PageRequestDTO requestDTO = PageRequestDTO.builder().build();

        PageResponseDTO<ProductListDTO> result
                = productRepository.list(requestDTO);

        log.info(result);

    }

    @Test
    public void testListOld() {

        Pageable pageable =
                PageRequest.of(0,10, Sort.by("pno").descending());

        Page<Product> result = productRepository.list1(pageable);

        result.get().forEach(product -> {
            log.info(product);
            log.info(product.getTags());
            log.info("-----------------");
        });
    }


}
