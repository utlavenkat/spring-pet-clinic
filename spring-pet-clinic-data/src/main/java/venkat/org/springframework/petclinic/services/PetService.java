package venkat.org.springframework.petclinic.services;

import venkat.org.springframework.petclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();

}
