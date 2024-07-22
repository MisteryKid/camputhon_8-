package com.example.project_nodev.controller;

import com.example.project_nodev.entity.Answer;
import com.example.project_nodev.entity.Cat;
import com.example.project_nodev.entity.User;
import com.example.project_nodev.repository.UserRepository;
import com.example.project_nodev.service.AnswerService;
import com.example.project_nodev.service.CatService;
import com.example.project_nodev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/cat")
public class MainController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CatService catService;
    @Autowired
    private UserService userService;
    @Autowired
    private AnswerService answerService;

    @GetMapping("/main")
    public String main(){

        return "main";
    }

    ///////////////////////////////////////////////// 동국이 실시간 사진 /////////////////////////////////////////////////
    @GetMapping("/location")
    public String boardList(Model model,
                            @PageableDefault(page = 0,size = 10,sort = "id",direction = Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(name="searchKeyword", required = false) String searchKeyword){

        Page<Cat> list = null;

        if(searchKeyword == null) {
            list = catService.catList(pageable);
        }else {
            list = catService.catSearchList(searchKeyword, pageable);
        }


        int nowPage= list.getPageable().getPageNumber() +1;
        int startPage= Math.max(nowPage -4,1) ;
        int endPage= Math.min(nowPage +5,list.getTotalPages());

        model.addAttribute("list",list);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        return "location";
    }

    ///////////////////////////////////////////////// 병원 /////////////////////////////////////////////////
    @GetMapping("/friends")
    public String hospital(){

        return "friends";
    }

    ///////////////////////////////////////////////// 기부 /////////////////////////////////////////////////
    /* 결제 api 구현 복잡한 관계로 폐기
    @GetMapping("/donate")
    public String donate(){

        return "donate";
    }
*/
    ///////////////////////////////////////////////// 소개 페이지 /////////////////////////////////////////////////
    @GetMapping("/explain")
    public String explain(){

        return "explain";
    }


    ///////////////////////////////////////////////// 로그인 페이지 /////////////////////////////////////////////////
    /* 기능 구현 과정 중 에러가 너무 뜨는 관계로 폐기
    @GetMapping("/login")
    public String login() {

        return "login";
    }
    @PostMapping("/loginpro")
    public String login(@RequestParam("userid") String userid, @RequestParam("password") String password,
                        Model model) {
        User user = userRepository.findByUseridAndPassword(userid, password);
        System.out.println(userid);
        System.out.println(password);
        if (user != null) {
            // 로그인 성공 시 메인 페이지로 이동
            return "redirect:/cat/main";
        } else {
            // 로그인 실패 시 에러 메시지 표시
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "login";
        }
    }

    ///////////////////////////////////////////////// 회원가입 페이지 /////////////////////////////////////////////////
    @GetMapping("/sign")
    public String sign() {
        return "sign";
    }

    @PostMapping("/signpro")
    public String signpro(User user){

        userService.write(user);
        return "login";
    }

*/
    ///////////////////////////////////////////////// 동국이 사진 올리기 /////////////////////////////////////////////////
    @GetMapping("/location/write")
    public String listwrite(){

        return "listwrite";
    }
    //여기 주소 좀 이상함 확인 ㄱ

    @PostMapping("/location/writepro")
    public String locationwritepro(Model model, Cat cat, @RequestParam(name="file", required = false) MultipartFile file) throws Exception {

        catService.write(cat, file);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/cat/location");


        return "message";
    }


    ///////////////////////////////////////////////// 사진 수정 /////////////////////////////////////////////////

    @GetMapping("/location/modify/{id}")
    public String catModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("cat",catService.catView(id));
        return "modify";
    }

    @PostMapping("/location/update/{id}")
    public String catUpdate(@PathVariable("id") Integer id, Cat cat,
                              @RequestParam(name="file", required = false) MultipartFile file)
            throws Exception {

        Cat catTemp = catService.catView(id);
        catTemp.setContent(cat.getContent());
        catTemp.setTitle(cat.getTitle());
        catTemp.setFilepath(cat.getFilepath());
        catTemp.setFilename(cat.getFilename());

        catService.write(catTemp, file);

        return "redirect:/cat/location";

        //return "redirect:/cat/location";
    }

    @GetMapping("/location/view")
    public String boardView(Model model, Integer id) {

        model.addAttribute("cat", catService.catView(id));
        return "view";
    }



    @GetMapping("/location/delete")
    public String boardDelete(Integer id) {

        catService.catDelete(id);

        return "redirect:/cat/location";
    }

    @PostMapping("/location/create/{id}")
    public String createAnswer(Answer answer, Model model, @PathVariable("id") Integer id, @RequestParam(value="content") String content) {
        Cat cat = catService.catView(id);
        answer.setCat(cat); // Answer 객체의 cat 필드 설정
        answerService.create(answer, content);
        model.addAttribute("answer", answerService.answerList());
        //catService.create(cat,content);
        return ("redirect:/cat/location");
    }


}
