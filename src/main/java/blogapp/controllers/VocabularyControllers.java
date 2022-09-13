package blogapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VocabularyControllers {

    @GetMapping("/vocabulary")
    public String vocabulary(Model model) {
        model.addAttribute("title", "Словарь");
        return "vocabulary";
    }

    @GetMapping("/vocabulary/phrasalVerbs")
    public String phrasalVerbs(Model model){
        return "phrasalVerbs";
    }
}
