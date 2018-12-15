package venkat.org.springframework.petclinic.services.map;

import venkat.org.springframework.petclinic.model.Pet;

import java.util.Set;

public class PetServiceMap extends AbstractMapService<Pet, Long> {

    @Override
    public Pet save(Pet pet) {
        return super.save(pet.getId(), pet);
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public void delete(Pet pet) {
        super.delete(pet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

}
