package notes.project.userdatasystem.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Все клиенты системы")
@AllArgsConstructor
@NoArgsConstructor
public class SystemClientListResponseDto {
    @ApiModelProperty(value = "Название системы")
    private String systemName;

    @ApiModelProperty(value = "Список клиентов")
    private List<ClientListItemDto> clients;
}
