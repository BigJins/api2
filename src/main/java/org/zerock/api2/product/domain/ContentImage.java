package org.zerock.api2.product.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//빌더 사용 안했음 여기에
public class ContentImage {

    private int ord;
    private String filename;


}