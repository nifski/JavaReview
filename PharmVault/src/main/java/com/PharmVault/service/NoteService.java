package com.PharmVault.service;

import com.PharmVault.dto.CreateNoteRequestDataTransferObject;
import com.PharmVault.dto.NoteDataTransferObject;
import com.PharmVault.entity.Note;
import com.PharmVault.entity.User;
import com.PharmVault.repo.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<NoteDataTransferObject> getNotesForUser(User user) {
        return noteRepository.findByUser(user)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public NoteDataTransferObject createNoteForUser(CreateNoteRequestDataTransferObject createRequest, User user) {
        if ((createRequest.getTitle() == null || createRequest.getTitle().isBlank()) &&
                (createRequest.getContent() == null || createRequest.getContent().isBlank())) {
            throw new IllegalArgumentException("A note must have a title or content.");
        }

        Note newNote = new Note();
        newNote.setTitle(createRequest.getTitle());
        newNote.setContent(createRequest.getContent());
        newNote.setUser(user);

        Note savedNote = noteRepository.save(newNote);
        return convertToDto(savedNote);
    }

    private NoteDataTransferObject convertToDto(Note note) {
        return new NoteDataTransferObject(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }
}

