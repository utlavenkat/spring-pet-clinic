package venkat.org.springframework.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import venkat.org.springframework.petclinic.model.Owner;

@Repository
public interface OwnerRepository extends CrudRepository<Owner,Long> {
    Owner findByLastName(String lastName);
    Iterable<Owner> findByLastNameContainingIgnoreCase(String lastName);
}
