class NotesAPI {
    static getAllNotes() {
        const encryptedNotes = localStorage.getItem("pharmvault-notes");
        const key = sessionStorage.getItem("pharmvault-key");

        if (!encryptedNotes || !key) {
            return [];
        }

        try {
            const bytes = CryptoJS.AES.decrypt(encryptedNotes, key);
            const decryptedJson = bytes.toString(CryptoJS.enc.Utf8);

            if (!decryptedJson) {
                alert("Decryption failed: Incorrect password or corrupted data.");
                sessionStorage.removeItem("pharmvault-key");
                window.location.href = "login.html";
                return [];
            }

            const notes = JSON.parse(decryptedJson);
            return notes.sort((a, b) => new Date(a.updated) > new Date(b.updated) ? -1 : 1);
        } catch (error) {
            console.error("Decryption error:", error);
            alert("An error occurred during decryption.");
            return [];
        }
    }

    static saveNote(noteToSave) {
        const notes = NotesAPI.getAllNotes();
        const existingNote = notes.find(note => note.id == noteToSave.id);

        if (existingNote) {
            existingNote.title = noteToSave.title;
            existingNote.body = noteToSave.body;
            existingNote.updated = new Date().toISOString();
        } else {
            noteToSave.id = Math.floor(Math.random() * 1000000);
            noteToSave.updated = new Date().toISOString();
            notes.push(noteToSave);
        }

        const key = sessionStorage.getItem("pharmvault-key");
        if (!key) {
            alert("Session key not found. Please log in again.");
            return;
        }

        const encryptedData = CryptoJS.AES.encrypt(JSON.stringify(notes), key).toString();
        localStorage.setItem("pharmvault-notes", encryptedData);
    }

    static deleteNote(id) {
        const notes = NotesAPI.getAllNotes();
        const newNotes = notes.filter(note => note.id != id);

        const key = sessionStorage.getItem("pharmvault-key");
        if (!key) {
            alert("Session key not found. Please log in again.");
            return;
        }

        const encryptedData = CryptoJS.AES.encrypt(JSON.stringify(newNotes), key).toString();
        localStorage.setItem("pharmvault-notes", encryptedData);
    }
}

class App {
    constructor(root) {
        this.root = root;
        this.notes = [];
        this.activeNote = null;

        this.sidebar = this.root.querySelector(".notes-sidebar");
        this.notesListContainer = this.root.querySelector(".notes-list");
        this.addNoteBtn = this.root.querySelector(".add-note-btn");
        this.titleInput = this.root.querySelector(".note-title-input");
        this.bodyInput = this.root.querySelector(".note-body-input");
        this.logoutBtn = this.root.querySelector("#logout-btn");

        this.addNoteBtn.addEventListener("click", () => this.onAddNote());

        this.logoutBtn.addEventListener("click", () => {
            sessionStorage.removeItem("pharmvault-key");
            window.location.href = "login.html";
        });

        [this.titleInput, this.bodyInput].forEach(field => {
            field.addEventListener("blur", () => {
                const newTitle = this.titleInput.value.trim();
                const newBody = this.bodyInput.value.trim();
                this.onNoteEdit(newTitle, newBody);
            });
        });

        this.refreshNotes();
    }

    refreshNotes() {
        const notes = NotesAPI.getAllNotes();
        this.notes = notes;
        this.renderNotesList();
        this.renderActiveNote();
    }

    renderNotesList() {
        this.notesListContainer.innerHTML = "";

        if (this.notes.length === 0) {
            this.notesListContainer.innerHTML = `<div class="notes-list-empty">No notes yet.</div>`;
            return;
        }

        for (const note of this.notes) {
            const listItem = this.createListItemHTML(note.id, note.title, note.body, note.updated);
            this.notesListContainer.appendChild(listItem);
        }

        this.notesListContainer.querySelectorAll(".note-item").forEach(item => {
            item.addEventListener("click", () => this.onNoteSelect(item.dataset.noteId));

            item.addEventListener("dblclick", () => {
                const doDelete = confirm("Are you sure you want to delete this note?");
                if (doDelete) {
                    this.onNoteDelete(item.dataset.noteId);
                }
            });
        });
    }

    renderActiveNote() {
        if (!this.activeNote) {
            this.titleInput.value = "";
            this.bodyInput.value = "";
            this.titleInput.style.visibility = "hidden";
            this.bodyInput.style.visibility = "hidden";
            return;
        }

        this.titleInput.style.visibility = "visible";
        this.bodyInput.style.visibility = "visible";
        this.titleInput.value = this.activeNote.title;
        this.bodyInput.value = this.activeNote.body;
    }

    onAddNote() {
        const placeholders = [
            "Spill your secrets...",
            "Grocery shopping lol",
            "Math homework",
            "Top secret formula...",
            "Plans for world domination?",
            "My next brilliant idea..."
        ];

        const randomPlaceholder = placeholders[Math.floor(Math.random() * placeholders.length)];

        const newNote = {
            title: "New Note",
            body: randomPlaceholder
        };

        NotesAPI.saveNote(newNote);
        this.refreshNotes();

        this.activeNote = this.notes[0];
        this.renderActiveNote();
    }

    onNoteSelect(noteId) {
        this.activeNote = this.notes.find(note => note.id == noteId);
        this.renderActiveNote();

        this.notesListContainer.querySelectorAll(".note-item").forEach(item => {
            item.classList.remove("note-item--selected");
        });

        this.root.querySelector(`.note-item[data-note-id="${noteId}"]`).classList.add("note-item--selected");
    }

    onNoteEdit(newTitle, newBody) {
        if (!this.activeNote) return;

        NotesAPI.saveNote({
            id: this.activeNote.id,
            title: newTitle,
            body: newBody
        });
        this.refreshNotes();
    }

    onNoteDelete(noteId) {
        NotesAPI.deleteNote(noteId);
        this.activeNote = this.notes.length > 0 ? this.notes[0] : null;
        this.refreshNotes();
    }

    createListItemHTML(id, title, body, updated) {
        const MAX_BODY_LENGTH = 60;
        const listItem = document.createElement("li");
        listItem.classList.add("note-item");
        listItem.dataset.noteId = id;

        listItem.innerHTML = `
            <div class="note-item-title">${title || "New Note"}</div>
            <div class="note-item-body">
                ${body.substring(0, MAX_BODY_LENGTH)}
                ${body.length > MAX_BODY_LENGTH ? "..." : ""}
            </div>
            <div class="note-item-updated">
                ${new Date(updated).toLocaleString(undefined, {
            dateStyle: "full",
            timeStyle: "short"
        })}
            </div>
        `;
        return listItem;
    }
}

const appElement = document.querySelector(".app-container");
const app = new App(appElement);

document.addEventListener('DOMContentLoaded', () => {
    const themeToggleButton = document.getElementById('theme-toggle');
    const body = document.body;

    const applyTheme = (theme) => {
        if (theme === 'dark') {
            body.classList.add('dark-mode');
            themeToggleButton.textContent = '☀️';
        } else {
            body.classList.remove('dark-mode');
            themeToggleButton.textContent = '🌙';
        }
    };

    const currentTheme = localStorage.getItem('notes-theme');
    if (currentTheme) {
        applyTheme(currentTheme);
    } else {
        applyTheme('light');
    }

    themeToggleButton.addEventListener('click', () => {
        let newTheme = body.classList.contains('dark-mode') ? 'light' : 'dark';
        localStorage.setItem('notes-theme', newTheme);
        applyTheme(newTheme);
    });
});
