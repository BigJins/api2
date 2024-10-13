package org.zerock.api2.product.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

@Entity
@ToString(exclude = "product")
@Table(name = "tbl_review", indexes = {
        @Index(name = "idx_review_product", columnList = "product_pno")
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String reviewer;

    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ElementCollection
    @CollectionTable(name = "tbl_review_img")
    @Builder.Default
    @Getter
    @BatchSize(size = 20)
    private Set<ContentImage> images = new HashSet<>();

    public void addFile(String filename){
        ContentImage image = new ContentImage(images.size(),filename);
        images.add(image);
    }

    public void changeImages(Set<ContentImage> images) {
        this.images = images;
    }
}