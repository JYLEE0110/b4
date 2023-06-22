package org.zerock.b4.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.b4.dto.PageRequestDTO;
import org.zerock.b4.dto.ProductDTO;
import org.zerock.b4.dto.ProductRegisterDTO;
import org.zerock.b4.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    // 의존성 주입 
    private final ProductService productService;

    // Product update
    //step1 get방식
    @GetMapping("/modify/{pno}")
    public String modifyGet(
        @PathVariable("pno") Integer pno,
        PageRequestDTO pageRequestDTO,
        Model model){
    // 서비스에서 상품 조회 Model에 담아준다.
    // 상품 조회 기능이 없다. -> DTO 필요, Mapper필요, 서비스 필요

        // 상품 조회 서비스 호출 값 전달 
        ProductDTO dto = productService.get(pno);

        // model add Attribute
        // html 로 넘겨주기위한 데이터 정의 
        model.addAttribute("dto", dto);

        return "/product/modify";
    }


    // 이미지 조회 
    @GetMapping("/images/{pno}")
    @ResponseBody   // responseBody 로 json 데이터 넘겨준다  restController 가 아닐때 json 방식으로 변환하려면 @ResponseBody 를 써야함 
    public List<String> getImages(@PathVariable("pno") Integer pno) {

        return productService.getImage(pno);
    }

    //step3 post로 상품 수정
    @PostMapping("/modify/{pno}")
    public String modifyPost(@PathVariable("pno") Integer pno, ProductDTO dto){
        // pno 의 값을 일치시켜야 하기 때문에 강제적으로 dto.setPno(pno) 로 절대! 강제 세팅 ! : 권성준
        dto.setPno(pno);
        // 서비스 호출 값 전달 
        productService.modify(dto);

        // read page 로 리다이렉트를 보내야 하기때문에 pno 값을 실어서 보내준다
        return "redirect:/product/read/" + pno;
    }


    // list 
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        
        log.info("GET | /product/list");

        model.addAttribute("res", productService.list(pageRequestDTO));

    }

    @GetMapping("/register")
    public void register(){
        log.info("GET | /product/register");
    }

    @PostMapping("/register")
    public String registerPost(
        ProductRegisterDTO registerDTO,
        // Rediret 페이지 시 딱 한번만 pno를 보내서 모달창을 띄우기 위해
        RedirectAttributes rttr
    ){

        log.info("POST | /product/register");
        log.info(registerDTO);

        Integer pno = productService.register(registerDTO);

        log.info("New Pno"+pno);

        // Flash가 없으면 쿼리스트링으로 따라 붙는다.
        rttr.addFlashAttribute("result", pno);

        return "redirect:/product/list";
    }
    
}
