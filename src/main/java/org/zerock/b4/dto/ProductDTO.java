package org.zerock.b4.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
    
    private Integer pno;
    private String pname;
    private int price;
    private boolean status;
    private String regDate, modDate;

    // 쉬운 방법
    private List<String> fileNames;
}
