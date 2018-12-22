package venkat.org.springframework.petclinic.bootstrap;


import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.model.PetType;
import venkat.org.springframework.petclinic.model.Vet;
import venkat.org.springframework.petclinic.services.OwnerService;
import venkat.org.springframework.petclinic.services.PetService;
import venkat.org.springframework.petclinic.services.PetTypeService;
import venkat.org.springframework.petclinic.services.VetService;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class LoadData implements CommandLineRunner {

    private final OwnerService ownerService;

    private final VetService vetService;

    private final PetService petService;

    private final PetTypeService petTypeService;


    @Override
    public void run(String... args) {

        PetType dog = new PetType("Dog");
        PetType cat = new PetType("Cat");

        Pet pet1 = new Pet();
        pet1.setName("Johnny");
        pet1.setBirthDate(LocalDate.now());
        pet1.setPetType(dog);

        Pet pet2 = new Pet();
        pet2.setBirthDate(LocalDate.now());
        pet2.setPetType(cat);
        pet2.setName("Tommy");

        Owner owner1 = new Owner();
        owner1.setFirstName("Venkat");
        owner1.setLastName("Utla");
        owner1.setAddress("HIG-68,KPHB");
        owner1.setCity("Hyderabad");
        owner1.setTelephone("9100912536");
        owner1.getPets().add(pet1);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Lakshmi");
        owner2.setLastName("Utla");
        owner2.setAddress("HIG-68,KPHB Colony");
        owner2.setTelephone("9186599677");
        owner2.getPets().add(pet2);
        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Hanshitha");
        vet1.setLastName("Utla");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Divnesh");
        vet2.setLastName("Gopisetty");

        vetService.save(vet2);

        System.out.println("Owners size::" + ownerService.findAll().size());
        System.out.println("Veterian size::" + vetService.findAll().size());
        System.out.println("Pet size::" + petService.findAll().size());
        System.out.println("Pet Types Size::"+petTypeService.findAll().size());

    }
}
