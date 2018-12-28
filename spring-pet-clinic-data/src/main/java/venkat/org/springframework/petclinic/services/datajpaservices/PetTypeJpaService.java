package venkat.org.springframework.petclinic.services.datajpaservices;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import venkat.org.springframework.petclinic.model.PetType;
import venkat.org.springframework.petclinic.repositories.PetTypeRepository;
import venkat.org.springframework.petclinic.services.PetTypeService;

import java.util.HashSet;
import java.util.Set;

@Service
@Qualifier("petTypeServiceJpa")
@NoArgsConstructor
@AllArgsConstructor
@Profile({"springdatajpa"})
public class PetTypeJpaService implements PetTypeService {
    @Autowired
    private PetTypeRepository petTypeRepository;

    @Override
    public Set<PetType> findAll() {
        Set<PetType> petTypes = new HashSet<>();
        petTypeRepository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public PetType findById(Long id) {
        return petTypeRepository.findById(id).orElse(null);
    }

    @Override
    public PetType save(PetType petType) {
        return petTypeRepository.save(petType);
    }

    @Override
    public void delete(PetType petType) {
        petTypeRepository.delete(petType);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }
}
