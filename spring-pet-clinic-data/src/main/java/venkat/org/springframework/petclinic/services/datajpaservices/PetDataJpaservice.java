package venkat.org.springframework.petclinic.services.datajpaservices;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.repositories.PetRepository;
import venkat.org.springframework.petclinic.services.PetService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Qualifier("petServiceJpa")
public class PetDataJpaservice implements PetService {

    private final PetRepository petRepository;

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public void delete(Pet pet) {
        petRepository.delete(pet);

    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);

    }
}
