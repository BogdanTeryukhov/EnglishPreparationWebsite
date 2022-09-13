package blogapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UnitsController {

    @GetMapping("/unit1")
    public String unit1(Model model){
        return "unit1";
    }
}
