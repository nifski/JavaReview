#README

PharmVault is a private and secure digital notebook. Its main purpose is to give users a safe place to write down their thoughts, with the guarantee that their notes are for their eyes only.

The most important goal of this project is to ensure that each user's account is completely isolated and secure from all others.




The database is built with strict rules to protect user data from the very beginning:

If a user decides to delete their account, all of their notes are instantly and automatically deleted. This ensures no leftover "orphaned" data ever exists in the system.

The database automatically records the exact time a note is created. The application doesn't have to worry about this, making the system more reliable.

A note must have either a title or some content. While the title can be left blank, the main content area cannot be, ensuring a user can't save a completely empty note.




The application's code enforces one unbreakable rule(A user can only ever see, edit, or delete their own notes):

When a new user signs up, the system checks that their username is a reasonable length, their email looks like a real email address, and their password is strong enough to be secure.

The system ensures no two users can sign up with the same username or email address.

User passwords are never stored directly. They are scrambled using BCrypt. This means that even if someone gained access to the database, they would not be able to see the actual passwords.

If a login attempt fails, the system gives a generic "invalid credentials" error. It never says "user not found" or "wrong password." This is a key security feature to prevent attackers from guessing which usernames are valid.

After a user logs in, the system gives them a temporary, secure digital key. This key is required for all future actions, like creating or viewing notes, proving their identity without needing to send their password again.(JWT)

The entire sign-up process is treated as a single, all-or-nothing operation. If any part of it fails, the whole process is cancelled, preventing broken or incomplete user accounts from being created.

Every time a user tries to view, update, or delete a note, the system first performs a strict check to confirm they are the legitimate owner using their JWT token.

When a new note is saved, its ownership is assigned based on the user's secure digital key. The system ignores any attempt by a user to manually assign a note to someone else.

The system is careful to only show the necessary information to the user. Sensitive internal data (like password hashes or other users' information) is never exposed.

To keep the application fast, a user's notes are loaded in pages (e.g., 10 at a time) rather than all at once.

Users can sort their notes by when they were created or last updated.




TECH STACK-
Java
Spring Boot
Spring Security
Spring Data JPA
PostgreSQL
Maven