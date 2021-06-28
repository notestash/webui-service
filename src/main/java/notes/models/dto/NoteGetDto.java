package notes.models.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
public class NoteGetDto {
    private Long id;
    private String message;
    private Date createdDate;
}
