package notes.project.userdatasystem.validation.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ChangeClientPersonalInfoValidationDto {
    private Boolean emailAlreadyInUse;
    private List<String> existingTypes;
    private List<String> typesFromRequest;
    private Boolean createNewTypes;
}
