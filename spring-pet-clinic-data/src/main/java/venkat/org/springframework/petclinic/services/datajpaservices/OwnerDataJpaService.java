package venkat.org.springframework.petclinic.services.datajpaservices;

import lombok.AllArgsConstructor;
import lombok.val;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import venkat.org.springframework.petclinic.model.Owner;
import venkat.org.springframework.petclinic.repositories.OwnerRepository;
import venkat.org.springframework.petclinic.services.OwnerService;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Qualifier("ownerServiceJpa")
@Profile({"springdatajpa"})
public class OwnerDataJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        val owners = new HashSet<Owner>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }
}
