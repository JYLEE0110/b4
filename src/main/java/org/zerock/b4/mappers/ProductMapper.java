package org.zerock.b4.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    // 상품 테이블 수정 
    // ※ MyBtis에 $도 쓸 수 있다. => 값이 바로 대입된다.
    @Update("update tbl_product set pname = #{pname}, price = #{price}, status = #{status} where pno = #{pno}")
    int updateOne(ProductDTO productDTO);

    // 이미지 삭제 후 수정
    // delete는 Rollback 가능 Truncate(대량의 데이터 삭제할 시)는 Rollback 불가능
    @Delete("delete from tbl_product_img where pno = #{pno}")
    int deleteimges(Integer pno);

}
