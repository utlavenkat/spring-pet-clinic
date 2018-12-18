package venkat.org.springframework.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import venkat.org.springframework.petclinic.services.OwnerService;

@Controller
public class OwnerController {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping(value = {"/owners", "/owners/index", "/owners/index.html"}, method = {RequestMethod.GET})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }


}
