package annotation;

import annotation.aop.Traceable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class TestController {

    @PostMapping("/test")
    @Traceable
    public String test(@RequestBody Map<String, String> input, HttpServletRequest servletRequest) {

        return "success";
    }
}
