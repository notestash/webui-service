package notes.controllers;

import lombok.RequiredArgsConstructor;
import notes.clients.NoteServiceClient;
import notes.models.dto.NoteSaveDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.constraints.NotNull;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final NoteServiceClient noteServiceClient;

    @GetMapping("/")
    public String getHome() {
        return "home";
    }

    @GetMapping("/dashboard")
    public String getDefaultDashboard(Model model) {
        NoteSaveDto noteSaveDto = new NoteSaveDto();
        model.addAttribute("noteSaveDto", noteSaveDto);
        model.addAttribute("notes", noteServiceClient.getNotes());
        return "dashboard";
    }

    @GetMapping("/dashboard/users/{userId}")
    public String getDefaultDashboard(@PathVariable("userId") String userId, Model model) {
        NoteSaveDto noteSaveDto  = new NoteSaveDto();
        model.addAttribute("noteSaveDto", noteSaveDto);
        model.addAttribute("notesGetDtoList", noteServiceClient.getNotes(userId));
        model.addAttribute("userId", userId);
        return "dashboard";
    }

    @PostMapping("/dashboard/users/{userId}")
    public String postNewNote(@PathVariable("userId") String userId, @ModelAttribute NoteSaveDto noteSaveDto,
                              @NotNull Model model) {
        NoteSaveDto savedNoteDto = noteServiceClient.postNote(noteSaveDto);
        if (savedNoteDto == null) {
            model.addAttribute("error", "Could not save note");
        }
        model.addAttribute("notesGetDtoList",
                noteServiceClient.getNotes(userId)
        );
        return "dashboard";
    }

}
