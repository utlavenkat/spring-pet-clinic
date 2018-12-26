package venkat.org.springframework.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import venkat.org.springframework.petclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit,Long> {
}
