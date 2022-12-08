package com.hyerin.phonebook.controller;

import com.hyerin.phonebook.common.SessionConst;
import com.hyerin.phonebook.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){
        // 세션이 없으면 홈으로 이동
        HttpSession session = request.getSession(false);
        if(session == null){
            return "home";
        }

        // 세션에 저장된 회원 조회
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원 데이터가 없으면 홈으로 이동
        if(loginMember == null){
            return "home";
        }

        // 세션이 유지되면 로그인 홈으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
