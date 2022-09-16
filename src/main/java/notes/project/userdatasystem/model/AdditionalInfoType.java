package notes.project.userdatasystem.model;

import javax.persistence.*;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity(name = "additional_info_types")
@Accessors(chain = true)
public class AdditionalInfoType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "system_id")
    private System system;

    private String type;
}
