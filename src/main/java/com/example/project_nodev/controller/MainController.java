package com.example.project_nodev.controller;


import com.example.project_nodev.entity.Cat;
import com.example.project_nodev.entity.User;
import com.example.project_nodev.repository.UserRepository;
import com.example.project_nodev.service.CatService;
import com.example.project_nodev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cat")
public class MainController {

    private final UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CatService catService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/location")
    public String location(Model model){
        model.addAttribute("list",catService.catList());
        return "catlist";
    }

    @GetMapping("/hospital")
    public String hospital(){

        return "hospital";
    }

    @GetMapping("/donate")
    public String donate(){

        return "donate";
    }

    @GetMapping("/explain")
    public String explain(){

        return "explain";
    }

    @GetMapping("/user/mypage/charge/point")
    public @ResponseBody void chargePoint(Long amount){
        System.out.println(amount);

    }

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

    @GetMapping("/sign")
    public String sign() {
        return "sign";
    }

    @PostMapping("/signpro")
    public String signpro(User user){

        userService.write(user);
        return "login";
    }

    @GetMapping("/list")
    public String list(Model model){

        model.addAttribute("list",catService.catList());

        return "catlist";
    }

    @GetMapping("/listwrite")
    public String listwrite(){

        return "listwrite";
    }

    @PostMapping("/listwritepro")
    public String listwritepro(Cat cat){

        catService.write(cat);

        return "redirect:/cat/list";
    }
}
