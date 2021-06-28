package notes.clients;

import notes.models.dto.NoteGetDto;
import notes.models.dto.NoteSaveDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "NoteServiceClient", url = "${services.note-service.url}")
public interface NoteServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/notes/?size=100&sort=id,desc", consumes =
            MediaType.APPLICATION_JSON_VALUE)
    List<NoteGetDto> getNotes();

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/notes/?size=100&sort=id,desc", consumes =
            MediaType.APPLICATION_JSON_VALUE)
    List<NoteGetDto> getNotes(@PathVariable("userId") String id);

    @RequestMapping(method = RequestMethod.POST, value = "/notes/", consumes = MediaType.APPLICATION_JSON_VALUE)
    NoteSaveDto postNote(NoteSaveDto noteSaveDto);

}
