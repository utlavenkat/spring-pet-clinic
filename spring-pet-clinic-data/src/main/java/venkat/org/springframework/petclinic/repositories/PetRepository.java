package venkat.org.springframework.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import venkat.org.springframework.petclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet,Long> {
}
