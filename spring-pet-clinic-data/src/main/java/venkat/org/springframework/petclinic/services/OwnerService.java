package venkat.org.springframework.petclinic.services;

import venkat.org.springframework.petclinic.model.Owner;

public interface OwnerService extends CRUDService<Owner, Long> {

    Owner findByLastName(String lastName);

}
