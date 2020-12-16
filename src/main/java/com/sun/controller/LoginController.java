package com.sun.controller;

import com.sun.pojo.Books;
import com.sun.pojo.User;
import com.sun.service.BookService;
import com.sun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @RequestMapping("/login")
    public String login(User user, HttpSession session,Model model) {
        String userName = user.getUserName();
        String password = userService.loginCheck(userName);
        String psw = user.getPassWord();
        System.out.println(password + psw);
        if (psw != null && psw.equals(password)) {
            System.out.println("成功");
            session.setAttribute("userName",userName);
            List<Books> books = bookService.queryAllBook();
            model.addAttribute("list", books);
            return "redirect:/book/allBook";
        } else {
            System.out.println("失败");
            model.addAttribute("login_msg", "账号或密码错误！");
            return "login";
        }
    }

    @RequestMapping("/toRegistered")
    public String toRegistered() {
        return "/registered";
    }

    @RequestMapping("/registeredUser")
    public String registeredUser(User user,Model model) {
        User user1 = userService.queryUser(user.getUserName());
        if (user1 == null) {
            userService.registeredUser(user);
            model.addAttribute("login_msg", "注册成功");
            return "login";
        } else {
            model.addAttribute("login_msg", "该账户名已经存在");
            return "login";
        }
    }




    /*@RequestMapping("/checkCode")
    public String loginCheckCod() {

        return "";
    }*/
}
