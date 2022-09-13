package blogapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestsController {

    @GetMapping("/tests")
    public String testsMain(Model model){
        return "tests-main";
    }
}