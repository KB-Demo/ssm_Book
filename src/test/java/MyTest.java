import com.sun.pojo.Books;
import com.sun.pojo.User;
import com.sun.service.BookService;
import com.sun.service.BookServiceImpl;
import com.sun.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Iterator;
import java.util.List;

public class MyTest {
    @Test
    public void test() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookServiceImpl = context.getBean("BookServiceImpl", BookServiceImpl.class);

        bookServiceImpl.addBook(new Books(8,"数据结构", 2, "很难"));
        List<Books> books = bookServiceImpl.queryAllBook();
        for (Books book : books) {
            System.out.println(book);
        }
    }
    @Test
    public void test1() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService = context.getBean("BookServiceImpl", BookService.class);
        List<Books> books = bookService.queryBookByName("i");
        Iterator<Books> iterator = books.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
    @Test
    public void login() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServiceImpl = context.getBean("UserServiceImpl", UserService.class);
        String s = userServiceImpl.loginCheck("123456");
        System.out.println(s);
    }

   /* @Test
    public void registered() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServiceImpl = context.getBean("UserServiceImpl", UserService.class);
        User user = new User(3,"shabi","123456");
        userServiceImpl.registeredUser(user);
        User user1 = userServiceImpl.queryUser("shabi");
        System.out.println(user1);
    }*/
}
