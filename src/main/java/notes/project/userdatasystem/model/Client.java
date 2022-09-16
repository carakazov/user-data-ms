package notes.project.userdatasystem.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.*;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity(name = "clients")
@Accessors(chain = true)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "system_id")
    private System system;

    private String name;

    private String surname;

    private String middleName;

    private String email;

    private UUID externalId;

    private LocalDate dateOfBirth;

    private LocalDateTime registrationDate;
}
