package venkat.org.springframework.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "owners")
public class Owner extends Person {
    @Column(name = "address")
    @NonNull
    private String address;

    @Column(name = "city")
    @NonNull
    private String city;

    @Column(name = "telephone")
    @NonNull
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    public boolean isNew() {
        return this.getId() == null;
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name) {
        return getPet(name, false);
    }

    /**
     * Return the Pet with the given name, or null if none found for this Owner.
     *
     * @param name to test
     * @return true if pet name is already in use
     */
    public Pet getPet(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Pet pet : this.pets) {
            if (!ignoreNew || !pet.isNew()) {
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return pet;
                }
            }
        }
        return null;
    }

    public void addPet(Pet pet) {
        if (pet.isNew()) {
            getPets().add(pet);
        }
        pet.setOwner(this);
    }

}
