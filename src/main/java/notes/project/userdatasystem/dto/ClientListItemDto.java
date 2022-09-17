package notes.project.userdatasystem.dto;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Описание клиента расширенное")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ClientListItemDto extends ClientDto {
    @ApiModelProperty(value = "Дата регистрации")
    private LocalDateTime registrationDate;
}
