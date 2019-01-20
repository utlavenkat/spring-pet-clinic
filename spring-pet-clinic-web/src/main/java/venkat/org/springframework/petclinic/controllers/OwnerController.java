package venkat.org.springframework.petclinic.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.services.OwnerService;

import javax.websocket.server.PathParam;
import java.util.Set;

@Controller
@RequestMapping("/owners")
@Slf4j
public class OwnerController {

    private static final String VIEW_NAME_OWNER_LIST = "owners/index";
    private static final String VIEW_NAME_OWNER_DETAILS = "owners/ownerDetails";
    private static final String VIEW_NAME_FIND_OWNERS = "owners/findOwners";


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
    public ModelAndView displayOwner(@PathVariable final Long ownerId,final ModelAndView modelAndView){
        log.info("findOwner, owner id::" + ownerId);
        modelAndView.setViewName(VIEW_NAME_OWNER_DETAILS);
        modelAndView.getModel().put("owner",ownerService.findById(ownerId));
        return modelAndView;
    }

    @GetMapping("/findForm")
    public ModelAndView findOwnerForm(final ModelAndView modelAndView) {
        modelAndView.setViewName(VIEW_NAME_FIND_OWNERS);
        modelAndView.addObject("owner",new Owner());
        return modelAndView;
    }

    @GetMapping("/find")
    public String findOwnerByLastName(Owner owner,  BindingResult result,final Model model) {
        Set<Owner> owners = ownerService.findByLastNameLike(owner.getLastName());
        String viewName = "";
        if (CollectionUtils.isEmpty(owners)) {
            result.rejectValue("lastName", "notFound", "not found");
            viewName = VIEW_NAME_FIND_OWNERS;
        } else {
            int searchResultCount = CollectionUtils.size(owners);
            if (searchResultCount == 1) {
                model.addAttribute("owner", owners.iterator().next());
                viewName = VIEW_NAME_OWNER_DETAILS;
            } else {
                model.addAttribute("owners",owners);
                viewName = VIEW_NAME_OWNER_LIST;
            }

        }
        return viewName;
    }



}
