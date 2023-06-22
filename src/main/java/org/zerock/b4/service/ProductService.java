package org.zerock.b4.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.b4.dto.PageRequestDTO;
import org.zerock.b4.dto.PageResponseDTO;
import org.zerock.b4.dto.ProductDTO;
import org.zerock.b4.dto.ProductListDTO;
import org.zerock.b4.dto.ProductRegisterDTO;

@Transactional
public interface ProductService {
    
    // pno 상품번호 반환
    Integer register(ProductRegisterDTO registerDTO);

    // 부모 인터페이스로 빼서 상속
    PageResponseDTO<ProductListDTO> list(PageRequestDTO pageRequestDTO);
    
    // 상품 조회 
    ProductDTO get(Integer pno);

    // 이미지 목록 조회 
    List<String> getImage(Integer pno);
    
    // ※ Transcation
    // 상품 데이터 수정
    // 기존 첨부파일 DB 삭제
    // DTO에 있는 첨부파일 DB 추가

    // 상품 수정 
    void modify(ProductDTO productDTO);

    // 삭제 후 수정 을 위한 삭제 메소드 정의 
    


}
