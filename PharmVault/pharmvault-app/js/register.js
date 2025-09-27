document.addEventListener("DOMContentLoaded", () => {
    const registerForm = document.getElementById("register-form");
    const usernameInput = document.getElementById("username-input");
    const emailInput = document.getElementById("email-input");
    const passwordInput = document.getElementById("password-input");

    registerForm.addEventListener("submit", (e) => {
        e.preventDefault();

        const username = usernameInput.value.trim().toLowerCase();
        const email = emailInput.value.trim();
        const password = passwordInput.value;

        const allUsers = JSON.parse(localStorage.getItem("pharmvault-users") || "{}");

        if (allUsers[username]) {
            alert("Username already exists. Please choose another one.");
            return;
        }

        allUsers[username] = {
            email: email,
            encryptedNotes: ""
        };

        localStorage.setItem("pharmvault-users", JSON.stringify(allUsers));

        sessionStorage.setItem("pharmvault-currentUser", username);
        sessionStorage.setItem("pharmvault-key", password);

        window.location.href = "app.html"; // Updated Link
    });
});