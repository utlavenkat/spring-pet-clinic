package venkat.org.springframework.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@AllArgsConstructor
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
