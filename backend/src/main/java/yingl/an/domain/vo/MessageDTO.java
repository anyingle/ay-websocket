package yingl.an.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yingl.an.domain.enums.MessageType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private MessageType messageType;
    private String username;
    private String message;

}
