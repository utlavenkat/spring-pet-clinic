package venkat.org.springframework.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import venkat.org.springframework.petclinic.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality,Long> {
}
