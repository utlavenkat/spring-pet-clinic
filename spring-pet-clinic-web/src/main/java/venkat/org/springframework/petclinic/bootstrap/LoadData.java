package venkat.org.springframework.petclinic.bootstrap;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import venkat.org.springframework.petclinic.model.*;
import venkat.org.springframework.petclinic.services.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@AllArgsConstructor
@Slf4j
public class LoadData implements CommandLineRunner {

    private final OwnerService ownerService;

    private final VetService vetService;

    private final PetService petService;

    private final PetTypeService petTypeService;

    private final SpecialityService specialityService;

    private final VisitService visitService;

    @Override
    public void run(String... args) {


        PetType monkey = petTypeService.save(new PetType("Monkey"));
        PetType hen = petTypeService.save(new PetType("Hen"));

        Pet pet1 = new Pet();
        pet1.setName("Johnny");
        pet1.setBirthDate(LocalDate.now());
        pet1.setPetType(monkey);

        Pet pet2 = new Pet();
        pet2.setBirthDate(LocalDate.now());
        pet2.setPetType(hen);
        pet2.setName("Tommy");

        Owner owner1 = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner1.setFirstName("Venkat");
        owner1.setLastName("Utla");
        owner1.getPets().add(pet1);

        ownerService.save(owner1);

        Owner owner2 = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner2.setFirstName("Lakshmi");
        owner2.setLastName("Utla");
        owner2.setCity("Hyderabad");
        owner2.setAddress("HIG-68,KPHB Colony");
        owner2.setTelephone("9186599677");
        owner2.getPets().add(pet2);
        ownerService.save(owner2);

        Visit visit1 = new Visit(LocalTime.now(),"General Visit",pet1);
        visitService.save(visit1);

        Visit visit2 = new Visit(LocalTime.now(),"Fever",pet2);
        visitService.save(visit2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Hanshitha");
        vet1.setLastName("Utla");

        Speciality hanshithaSpeciality = new Speciality("General");
        vet1.getSpecialities().add(specialityService.save(hanshithaSpeciality));

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Divnesh");
        vet2.setLastName("Gopisetty");

        Speciality divneshSpeciality = new Speciality("Dietician");
        vet2.getSpecialities().add(specialityService.save(divneshSpeciality));
        vetService.save(vet2);

        log.info("Owners size::" + ownerService.findAll().size());
        log.info("Veterian size::" + vetService.findAll().size());
        log.info("Pet size::" + petService.findAll().size());
        log.info("Pet Types Size::"+petTypeService.findAll().size());
        log.info("Specialities Size::"+specialityService.findAll().size());
        log.info("Visits Size::"+ visitService.findAll().size());

    }
}
