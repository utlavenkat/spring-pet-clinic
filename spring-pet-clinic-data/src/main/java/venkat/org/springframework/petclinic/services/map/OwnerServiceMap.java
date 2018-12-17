package venkat.org.springframework.petclinic.services.map;

import org.springframework.stereotype.Service;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.services.OwnerService;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    @Override
    public Owner save(Owner owner) {
        return super.save(owner.getId(), owner);
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
