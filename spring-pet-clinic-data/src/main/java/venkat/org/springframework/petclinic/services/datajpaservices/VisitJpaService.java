package venkat.org.springframework.petclinic.services.datajpaservices;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import venkat.org.springframework.petclinic.model.Visit;
import venkat.org.springframework.petclinic.repositories.VisitRepository;
import venkat.org.springframework.petclinic.services.VisitService;

import java.util.HashSet;
import java.util.Set;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Profile({"springdatajpa"})
public class VisitJpaService implements VisitService {
    @Autowired
    private VisitRepository visitRepository;

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        visitRepository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Visit findById(Long id) {
       return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }
}
