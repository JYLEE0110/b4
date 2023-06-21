package org.zerock.b4.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.zerock.b4.dto.PageRequestDTO;
import org.zerock.b4.dto.ProductDTO;
import org.zerock.b4.dto.ProductListDTO;
import org.zerock.b4.dto.ProductRegisterDTO;

public interface ProductMapper {

    List<ProductListDTO> getList(PageRequestDTO pageRequestDTO);
    
    // 상품 등록
    int insertProduct(ProductRegisterDTO registerDTO);

    // 이미지 등록
    int insertImages(List<Map<String,String>> imageList);


    // Update
    // 상품 데이터 조회
    @Select("select * from tbl_product p where p.pno = #{pno}")
    ProductDTO selectOne(Integer pno);

    // 이미지 목록 조회
    @Select("select concat(uuid,'_',fileName) from tbl_product_img where pno=#{pno} order by ord")
    List<String> selectImages(Integer pno);

}
