package venkat.org.springframework.petclinic.services.map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.model.PetType;
import venkat.org.springframework.petclinic.model.Visit;
import venkat.org.springframework.petclinic.services.PetService;
import venkat.org.springframework.petclinic.services.PetTypeService;
import venkat.org.springframework.petclinic.services.VisitService;

import java.util.Set;

@Service
@Qualifier("visitServiceMap")
@Primary
@NoArgsConstructor
@AllArgsConstructor
@Profile({"default","map"})
public class VisitServiceMap extends AbstractMapService<Visit,Long> implements VisitService {

    @Autowired
    private PetService petService;

    @Autowired
    private PetTypeService petTypeService;

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit visit) {
        {
            if(visit != null) {
                Pet pet = visit.getPet();
                if(pet != null)  {
                    PetType petType = pet.getPetType();
                    if(petType != null) {
                        pet.setPetType(petTypeService.save(petType));
                    }
                    visit.setPet(petService.save(pet));
                } else {
                    throw new RuntimeException("Pet cannot be null");
                }
            } else {
                throw new RuntimeException("Object cannot be null");
            }
            return super.save(visit);
        }
    }

    @Override
    public void delete(Visit visit) {
        super.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
