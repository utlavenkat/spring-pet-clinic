package venkat.org.springframework.petclinic.services.datajpaservices;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import venkat.org.springframework.petclinic.model.Vet;
import venkat.org.springframework.petclinic.repositories.VetRepository;
import venkat.org.springframework.petclinic.services.VetService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Qualifier("vetServiceJpa")
@NoArgsConstructor
@AllArgsConstructor
@Profile({"springdatajpa"})
public class VetJpaService implements VetService {
    @Autowired
    private VetRepository vetRepository;

    @Override
    public Set<Vet> findAll() {
        Set<Vet> vets = new HashSet<>();
        vetRepository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        vetRepository.delete(vet);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }
}
