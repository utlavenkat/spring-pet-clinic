package venkat.org.springframework.petclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "types")
public class PetType extends BaseEntity {
    @NonNull
    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
