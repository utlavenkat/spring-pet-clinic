package venkat.org.springframework.petclinic.bootstrap;


import lombok.val;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import venkat.org.springframework.petclinic.model.*;
import venkat.org.springframework.petclinic.services.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
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


        val monkey = petTypeService.save(new PetType("Monkey"));
        val hen = petTypeService.save(new PetType("Hen"));

        val pet1 = new Pet();
        pet1.setName("Johnny");
        pet1.setBirthDate(LocalDate.now());
        pet1.setPetType(monkey);

        val pet2 = new Pet();
        pet2.setBirthDate(LocalDate.now());
        pet2.setPetType(hen);
        pet2.setName("Tommy");

        val owner1 = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner1.setFirstName("Venkat");
        owner1.setLastName("Utla");
        owner1.getPets().add(pet1);

        ownerService.save(owner1);

        val owner2 = new Owner("HIG-68,KPHB","Hyderabad","9100912536");
        owner2.setFirstName("Lakshmi");
        owner2.setLastName("Utla");
        owner2.setCity("Hyderabad");
        owner2.setAddress("HIG-68,KPHB Colony");
        owner2.setTelephone("9186599677");
        owner2.getPets().add(pet2);
        ownerService.save(owner2);

        val visit1 = new Visit(LocalTime.now(),"General Visit",pet1);
        visitService.save(visit1);

        val visit2 = new Visit(LocalTime.now(),"Fever",pet2);
        visitService.save(visit2);

        val vet1 = new Vet();
        vet1.setFirstName("Hanshitha");
        vet1.setLastName("Utla");

        val hanshithaSpeciality = new Speciality("General");
        vet1.getSpecialities().add(specialityService.save(hanshithaSpeciality));

        vetService.save(vet1);

        val vet2 = new Vet();
        vet2.setFirstName("Divnesh");
        vet2.setLastName("Gopisetty");

        val divneshSpeciality = new Speciality("Dietician");
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
