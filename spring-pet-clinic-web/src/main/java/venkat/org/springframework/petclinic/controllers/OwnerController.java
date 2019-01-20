package venkat.org.springframework.petclinic.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import venkat.org.springframework.petclinic.services.OwnerService;

@Controller
@RequestMapping("/owners")
@Slf4j
public class OwnerController {

    private static final String VIEW_NAME_OWNER_LIST = "owners/index";
    private static final String VIEW_NAME_OWNER_DETAILS = "owners/ownerDetails";

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping(value = {"/", "/index", "/index.html"}, method = {RequestMethod.GET})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return VIEW_NAME_OWNER_LIST;
    }

    @RequestMapping("/{ownerId}")
    public ModelAndView findOwner(@PathVariable final Long ownerId,final ModelAndView modelAndView){
        log.info("findOwner, owner id::" + ownerId);
        modelAndView.setViewName(VIEW_NAME_OWNER_DETAILS);
        modelAndView.getModel().put("owner",ownerService.findById(ownerId));
        return modelAndView;
    }


}
