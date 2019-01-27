package venkat.org.springframework.petclinic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.model.Visit;
import venkat.org.springframework.petclinic.services.PetService;
import venkat.org.springframework.petclinic.services.VisitService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class VisitController {

    private static final String VIEW_NAME_VISIT_FORM = "visits/createOrUpdateVisitForm";

    private final VisitService visitService;
    private final PetService petService;


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /**
     * Called before each and every @RequestMapping annotated method.
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use the session scope, make sure that Pet object always has an id
     * (Even though id is not part of the form fields)
     *
     * @param petId id of the pet.
     * @return Pet
     */
    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Map<String, Object> model) {
        Pet pet = petService.findById(petId);
        model.put("pet", pet);
        Visit visit = new Visit();
        pet.addVisit(visit);
        return visit;
    }

    // Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") int petId, Map<String, Object> model) {
        return VIEW_NAME_VISIT_FORM;
    }

    // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult result, @PathVariable("ownerId") Long ownerId) {
        if (result.hasErrors()) {
            return "visits/createOrUpdateVisitForm";
        } else {
            visitService.save(visit);
            return "redirect:/owners/" + ownerId;
        }
    }

}
