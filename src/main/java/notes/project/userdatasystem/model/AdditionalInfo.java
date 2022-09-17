package notes.project.userdatasystem.model;

import javax.persistence.*;

import liquibase.pro.packaged.J;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity(name = "additional_info")
@Accessors(chain = true)
public class AdditionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "additional_info_type_id")
    private AdditionalInfoType type;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String info;
}
