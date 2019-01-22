package venkat.org.springframework.petclinic.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.model.PetType;
import venkat.org.springframework.petclinic.services.OwnerService;
import venkat.org.springframework.petclinic.services.PetService;
import venkat.org.springframework.petclinic.services.PetTypeService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
@RequiredArgsConstructor
@Slf4j
public class PetController {

    private static final String VIEW_NAME_CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";

    private final PetTypeService petTypeService;
    private final PetService petService;
    private final OwnerService ownerService;

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner populateOwner(@PathVariable final Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    @GetMapping(path = "/pets/new")
    public String createPetForm(Owner owner, Model model) {
        Pet pet = new Pet();
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return VIEW_NAME_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping(path = "/pets/new")
    public String createPet(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.addPet(pet);
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEW_NAME_CREATE_OR_UPDATE_PET_FORM;
        }
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }

    @GetMapping(path = "/pets/{petId}/edit")
    public String editPetForm(@PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);
        model.addAttribute("pet", pet);
        return VIEW_NAME_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping(path = "/pets/{petId}/edit")
    public String updatePet(@Valid Pet pet, BindingResult result, Owner owner, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEW_NAME_CREATE_OR_UPDATE_PET_FORM;
        }
        owner.addPet(pet);
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }

}
