package org.zerock.api2.product.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String pname;

    private int price;

    private ProductStatus status;

    @ElementCollection
    @CollectionTable(name = "tbl_product_tag")
    private Set<String> tags = new HashSet<>();

    public void addTag(String tag) {
        tags.add(tag);
    }
    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public void clearTags() {
        tags.clear();
    }
}
