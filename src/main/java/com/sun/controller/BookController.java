package com.sun.controller;

import com.sun.pojo.Books;
import com.sun.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    //controller 调 service 层
    @Autowired
    //@Qualifier("BookServiceImpl")
    private BookService bookService;

    //查询全部书籍，并且返回到一个书籍展示页面
    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> books = bookService.queryAllBook();

        model.addAttribute("list", books);
        return "allBook";
    }

    //跳转到添加书籍页面
    @RequestMapping("/toAddPaper")
    public String toAddPaper() {
        return "addBook";
    }

    //添加书籍
    @RequestMapping("/addBook")
    public String addBook(Books book) {
        System.out.println(book);
        bookService.addBook(book);
        return "redirect:/book/allBook";
    }

    //跳转到修改书籍页面
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(int id, Model model){
        Books book = bookService.queryBookById(id);
        model.addAttribute("book", book);
        return "updateBook";
    }

    //修改书籍
    @RequestMapping("/updateBook")
    public String updateBook(Books book) {
        System.out.println(book);
        bookService.updateBook(book);
        return "redirect:/book/allBook";
    }

    //删除书籍
    //@RequestMapping("/del")
    @RequestMapping("/del/{bookId}")
    public String delBook(@PathVariable("bookId") int id) {
        System.out.println(id);
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }

    //查询书籍
    @RequestMapping("/queryBookByName")
    public String queryBookByName(String queryBookName,Model model) {
        List<Books> books = bookService.queryBookByName(queryBookName);
        if (books.size() == 0) {
            books  = bookService.queryAllBook();
            model.addAttribute("error", "未查到");
        }
        for (Books book : books) {
            System.out.println(book);
        }
        model.addAttribute("list", books);
        return "allBook";
    }


}
