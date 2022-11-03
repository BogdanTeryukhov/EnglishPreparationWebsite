package blogapp.controllers;

import blogapp.forTests.TravelAndTransportTest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/vocabulary")
public class VocabularyControllers {

    @GetMapping
    public String vocabulary(Model model) {
        model.addAttribute("title", "Словарь");
        return "vocabulary";
    }

    @GetMapping("/phrasalVerbs")
    public String phrasalVerbs(Model model){
        model.addAttribute("phrasals", "Phrasal Verbs");
        return "phrasalVerbs";
    }


    @GetMapping("/phrasalVerbs/travelAndTransport")
    public String travelAndTransport(Model model){
        model.addAttribute("travel", new TravelAndTransportTest());
        return "travelAndTransport";
    }

    @PostMapping("/phrasalVerbs/travelAndTransport")
    public String doTravel(@ModelAttribute("travel") @Valid TravelAndTransportTest test, Errors error){
        if (error.hasErrors()){
            return "travelAndTransport";
        }
        System.out.println(test);
        return "travelAndTransportSuccess";
    }
}
