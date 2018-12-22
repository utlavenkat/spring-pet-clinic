package venkat.org.springframework.petclinic.services.map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import venkat.org.springframework.petclinic.model.Vet;
import venkat.org.springframework.petclinic.services.SpecialityService;
import venkat.org.springframework.petclinic.services.VetService;

import java.util.Set;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    @Autowired
    private  SpecialityService specialityService;

    @Override
    public Vet save(Vet vet) {
        if(vet == null)
            throw new RuntimeException("Object cannot be null");
        if(!CollectionUtils.isEmpty(vet.getSpecialities())) {
            vet.getSpecialities().forEach(speciality -> {
                speciality.setId(specialityService.save(speciality).getId());
            });
        }
        return super.save(vet);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }
}
