package venkat.org.springframework.petclinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
@Data()
@Entity
@Table(name = "Pets")
public class Pet extends BaseEntity {

    @Column(name = "name")
    @NonNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @NonNull
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @NonNull
    @EqualsAndHashCode.Exclude
    private Owner owner;

    @Column(name = "birth_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    @EqualsAndHashCode.Exclude
    private Set<Visit> visits = new HashSet<>();

    public Visit addVisit(Visit visit) {
        visit.setPet(this);
        visits.add(visit);
        return visit;
    }

    public boolean isNew() {
        return this.getId() == null;
    }

    public String toString() {
        return name;
    }

}
