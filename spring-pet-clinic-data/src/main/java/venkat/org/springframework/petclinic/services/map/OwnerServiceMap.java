package venkat.org.springframework.petclinic.services.map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.services.OwnerService;
import venkat.org.springframework.petclinic.services.PetService;
import venkat.org.springframework.petclinic.services.PetTypeService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
                    val savedPet = petService.save(pet);
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
        val owners = super.findAll();
        return owners.stream().filter(owner -> owner.getLastName().equalsIgnoreCase(lastName)).findFirst().get();
    }

    @Override
    public Set<Owner> findByLastNameLike(String lastName) {
        return super.findAll().stream().filter(owner -> owner.getLastName().contains(lastName))
                .collect(Collectors.toSet());
    }
}
