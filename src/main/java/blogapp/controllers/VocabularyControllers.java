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
import java.util.Objects;

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
    public String doTravel(@ModelAttribute("travel") @Valid TravelAndTransportTest test, Errors error, Model model){
        if (error.hasErrors()){
            return "travelAndTransport";
        }

        int howManyRightAnswers = howManyRightAnswersInTravelAndTransportTest(test);
        model.addAttribute("howManyRightAnswers", howManyRightAnswers);

        System.out.println(test + "----" + howManyRightAnswers);
        return "travelAndTransportSuccess";
    }

    public Integer howManyRightAnswersInTravelAndTransportTest(TravelAndTransportTest test){
        int count = 0;
        if (Objects.equals(test.getSetOutOff().toLowerCase(), "set out")
                || Objects.equals(test.getSetOutOff(), "set off")
                || Objects.equals(test.getSetOutOff(), "set out / off")){
            count++;
        }
        if (Objects.equals(test.getCheckedIn().toLowerCase(), "checked in")){
            count++;
        }
        if (Objects.equals(test.getDropMeOff().toLowerCase(), "drop me off")){
            count++;
        }
        if (Objects.equals(test.getTurnAround().toLowerCase(), "turn around")){
            count++;
        }
        if (Objects.equals(test.getTakesOff().toLowerCase(), "takes off")){
            count++;
        }
        if (Objects.equals(test.getRunOver().toLowerCase(), "run over")){
            count++;
        }
        if (Objects.equals(test.getKeepUpWith().toLowerCase(), "keep up with")){
            count++;
        }
        return count;
    }
}
