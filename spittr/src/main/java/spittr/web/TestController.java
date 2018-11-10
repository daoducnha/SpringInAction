package spittr.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import spittr.model.Student;

@RestController
@RequestMapping("std")
public class TestController {

    @RequestMapping(value = "/student", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Student getStudent() {
        Student s = new Student();
        s.setId("1");
        s.setName("Nha");
        return s;
    }
}
