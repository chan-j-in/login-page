package com.study.loginPage.web;

import com.study.loginPage.dto.UserInfoDto;
import com.study.loginPage.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupPage() {
        return "signup"; // 'signup.html' 파일을 렌더링
    }

    @PostMapping("/user")
    private String signup(UserInfoDto infoDto) {
        userService.save(infoDto);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    private String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder
                .getContext().getAuthentication());
        return "redirect:/login";
    }
}
