package venkat.org.springframework.petclinic.services.map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.model.Pet;
import venkat.org.springframework.petclinic.services.OwnerService;
import venkat.org.springframework.petclinic.services.PetService;
import venkat.org.springframework.petclinic.services.PetTypeService;

import java.util.Set;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Primary
@Qualifier("ownerServiceMap")
@Profile({"default","map"})
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    @Autowired
    private PetTypeService petTypeService;
    @Autowired
    private PetService petService;

    @Override
    public Owner save(Owner owner) {
        if(owner != null) {
            if(!CollectionUtils.isEmpty(owner.getPets())) {
               owner.getPets().forEach(pet -> {
                   if(pet.getPetType() != null) {
                        pet.setPetType(petTypeService.save(pet.getPetType()));
                   } else {
                       throw new RuntimeException("PetType cannot be null");
                   }
                    Pet savedPet = petService.save(pet);
                    pet.setId(savedPet.getId());
               });
            }
        } else {
            throw new RuntimeException("Object cannot be null");
        }
        return super.save(owner);
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public Owner findByLastName(String lastName) {
        Set<Owner> owners = super.findAll();
        return owners.stream().filter(owner -> owner.getLastName().equalsIgnoreCase(lastName)).findFirst().get();
    }
}
