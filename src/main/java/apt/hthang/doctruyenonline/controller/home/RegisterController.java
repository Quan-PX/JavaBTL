package apt.hthang.doctruyenonline.controller.home;

import apt.hthang.doctruyenonline.entity.User;
import apt.hthang.doctruyenonline.service.CategoryService;
import apt.hthang.doctruyenonline.service.InformationService;
import apt.hthang.doctruyenonline.service.SecurityService;
import apt.hthang.doctruyenonline.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Huy Thang on 12/10/2018
 * @project truyenmvc
 */

@Controller
@PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
@RequestMapping("/dang-ky")
public class RegisterController {
    
    Logger logger = LoggerFactory.getLogger(RegisterController.class);
    
    @Value("${hthang.truyenonline.title.register}")
    private String titleRegisterPage;
    
    @Autowired
    private InformationService informationService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private SecurityService securityService;
    
    private void getMenuAndInfo(Model model, String title) {
        
        // Lấy Title Cho Page
        model.addAttribute("title", title);
        
        // Lấy List Category cho Menu
        model.addAttribute("categorylist", categoryService.getListCategoryOfMenu(1));
        
        // Lấy Information của Web
        model.addAttribute("information", informationService.getWebInfomation());
    }
    
    @GetMapping
    public String showRegisterForm(final Model model) {
        logger.info("Get Dang ky");
        // Lấy List Category Menu
        getMenuAndInfo(model, titleRegisterPage);
        
        model.addAttribute("user", new User());
        return "web/views/registerPage";
    }
    
    @PostMapping
    public String register(@Valid User user, BindingResult result, Model model, HttpServletRequest request) {
        boolean hasError = result.hasErrors();
        if (hasError) {
            getMenuAndInfo(model, titleRegisterPage);
            model.addAttribute("user", user);
            return "web/views/registerPage";
        }
        
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(8);
        user.setPassowrd(passwordEncoder.encode(user.getPasswordRegister()));

//        //Lưu Người dùng đăng ký trong database
//        userService.registerUser(user);
//
//        //Đăng nhập sau khi đăng ký thành công
//        securityService.autologin(user.getUsername(), user.getPasswordRegisterConfirm(), request);
        
        return "redirect:/";
    }
}