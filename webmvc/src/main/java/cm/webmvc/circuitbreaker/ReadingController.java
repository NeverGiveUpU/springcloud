package cm.webmvc.circuitbreaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈濛
 * @date 2020/4/23 5:17 下午
 */
@RestController
public class ReadingController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/to-read")
    public String toRead(@RequestParam String name) {
        return bookService.readingList(name);
    }
}
