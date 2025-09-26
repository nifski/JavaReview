package com.PharmVault;

import com.PharmVault.dto.CreateNoteRequestDataTransferObject;
import com.PharmVault.entity.Note;
import com.PharmVault.entity.User;
import com.PharmVault.repo.NoteRepository;
import com.PharmVault.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    private User testUser;
    private CreateNoteRequestDataTransferObject createNoteRequest;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");

        createNoteRequest = new CreateNoteRequestDataTransferObject();
        createNoteRequest.setTitle("Test Title");
        createNoteRequest.setContent("Test Content");
    }

    @Test
    void whenCreateNoteForUser_thenSavesNote() {
        when(noteRepository.save(any(Note.class))).thenAnswer(invocation -> invocation.getArgument(0));
        var result = noteService.createNoteForUser(createNoteRequest, testUser);

        assertNotNull(result);
        assertEquals("Test Title", result.getTitle());
        verify(noteRepository, times(1)).save(any(Note.class));
    }

    @Test
    void whenCreateBlankNote_thenThrowsException() {
        createNoteRequest.setTitle("");
        createNoteRequest.setContent(null);

        assertThrows(IllegalArgumentException.class, () -> {
            noteService.createNoteForUser(createNoteRequest, testUser);
        });
        verify(noteRepository, never()).save(any(Note.class));
    }

    @Test
    void whenGetNotesForUser_thenReturnCorrectNotes() {
        Note note1 = new Note();
        note1.setTitle("Note 1");
        Note note2 = new Note();
        note2.setTitle("Note 2");
        when(noteRepository.findByUser(testUser)).thenReturn(List.of(note1, note2));

        var result = noteService.getNotesForUser(testUser);

        assertEquals(2, result.size());
        assertEquals("Note 1", result.get(0).getTitle());
    }
}