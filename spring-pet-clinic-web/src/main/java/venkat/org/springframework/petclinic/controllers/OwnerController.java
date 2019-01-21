package venkat.org.springframework.petclinic.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.services.OwnerService;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/owners")
@Slf4j
public class OwnerController {

    private static final String VIEW_NAME_OWNER_LIST = "owners/index";
    private static final String VIEW_NAME_OWNER_DETAILS = "owners/ownerDetails";
    private static final String VIEW_NAME_FIND_OWNERS = "owners/findOwners";
    private static final String VIEW_NAME_CREATE_UPDATE_OWNER_FORM = "owners/createOrUpdateForm";


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

    @RequestMapping("/{ownerId}/edit")
    public ModelAndView editOwner(@PathVariable final Long ownerId, final ModelAndView modelAndView) {
        log.info("editOwner, owner id::" + ownerId);
        modelAndView.setViewName(VIEW_NAME_CREATE_UPDATE_OWNER_FORM);
        modelAndView.getModel().put("owner", ownerService.findById(ownerId));
        return modelAndView;
    }

    @PostMapping("/{ownerId}/edit")
    public String saveOwner(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") Long ownerId) {
        log.info("saveOwner, owner id::" + ownerId);
        if (result.hasErrors()) {
            return VIEW_NAME_CREATE_UPDATE_OWNER_FORM;
        }
        owner.setId(ownerId);
        ownerService.save(owner);
        return "redirect:/owners/" + ownerId;
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
        String viewName;
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

    @GetMapping(path = "/new")
    public String createForm(final Model model) {
        model.addAttribute("owner", new Owner());
        return VIEW_NAME_CREATE_UPDATE_OWNER_FORM;
    }

    @PostMapping(path = "/new")
    public String createOwner(@Valid Owner owner, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return VIEW_NAME_CREATE_UPDATE_OWNER_FORM;
        }
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

}
