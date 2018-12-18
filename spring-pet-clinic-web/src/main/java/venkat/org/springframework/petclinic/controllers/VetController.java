package venkat.org.springframework.petclinic.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import venkat.org.springframework.petclinic.services.VetService;

@Controller
@AllArgsConstructor
public class VetController {
    private final VetService vetService;

    @RequestMapping(value = {"/vets", "/vets/index", "/vets/index.html"}, method = {RequestMethod.GET})
    public String listVets(Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }
}
