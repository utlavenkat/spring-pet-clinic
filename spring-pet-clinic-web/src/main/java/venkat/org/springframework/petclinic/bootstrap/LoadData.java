package venkat.org.springframework.petclinic.bootstrap;


import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.model.Vet;
import venkat.org.springframework.petclinic.services.OwnerService;
import venkat.org.springframework.petclinic.services.PetService;
import venkat.org.springframework.petclinic.services.VetService;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class LoadData implements CommandLineRunner {

    private final OwnerService ownerService;

    private final VetService vetService;

    private final PetService petService;


    @Override
    public void run(String... args) {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Venkat");
        owner1.setLastName("Utla");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Lakshmi");
        owner2.setLastName("Utla");

        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Hanshitha");
        vet1.setLastName("Utla");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Divnesh");
        vet2.setLastName("Gopisetty");

        vetService.save(vet2);

        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setOwner(owner1);
        pet1.setBirthDate(LocalDate.now());
        pet1.setPetType(null);

        petService.save(pet1);

        Pet pet2 = new Pet();
        pet2.setId(2L);
        pet2.setOwner(owner2);
        pet2.setBirthDate(LocalDate.now());

        petService.save(pet2);

        System.out.println("Owners size::" + ownerService.findAll().size());
        System.out.println("Veterian size::" + vetService.findAll().size());
        System.out.println("Pet size::" + petService.findAll().size());

    }
}
