package com.PharmVault.controller;

import com.PharmVault.dto.CreateNoteRequestDataTransferObject;
import com.PharmVault.dto.NoteDataTransferObject;
import com.PharmVault.entity.User;
import com.PharmVault.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<NoteDataTransferObject>> getMyNotes(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(noteService.getNotesForUser(user));
    }

    @PostMapping
    public ResponseEntity<NoteDataTransferObject> createNote(
            @RequestBody CreateNoteRequestDataTransferObject createRequest,
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.ok(noteService.createNoteForUser(createRequest, user));
    }
}
