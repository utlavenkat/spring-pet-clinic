package venkat.org.springframework.petclinic.services.map;

import venkat.org.springframework.petclinic.model.Owner;

import java.util.Set;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> {

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
}
