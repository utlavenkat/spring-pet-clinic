package venkat.org.springframework.petclinic.services;

import venkat.org.springframework.petclinic.model.Owner;

import java.util.Set;

public interface OwnerService extends CRUDService<Owner, Long> {

    Owner findByLastName(String lastName);
    Set<Owner> findByLastNameLike(String lastName);

}
