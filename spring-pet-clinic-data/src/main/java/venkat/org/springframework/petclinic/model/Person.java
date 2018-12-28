package venkat.org.springframework.petclinic.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
@Data
@MappedSuperclass
public class Person extends BaseEntity {
    @Column(name = "first_name")
    @NonNull
    private String firstName;
    @Column(name = "last_name")
    @NonNull
    private String lastName;
}
