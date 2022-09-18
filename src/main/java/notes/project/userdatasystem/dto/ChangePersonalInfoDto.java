package notes.project.userdatasystem.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Изменение личных данных")
public class ChangePersonalInfoDto {
    @ApiModelProperty(value = "Новые значения базовых параметров")
    private Map<ChangePersonalInfo, String> newValues;
    @ApiModelProperty(value = "Изменения дополнительных параметров")
    private List<ChangeAdditionalInfoDto> changeAdditionalInfo = new ArrayList<>();
}
