document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("login-form");
    const usernameInput = document.getElementById("username-input");
    const passwordInput = document.getElementById("password-input");

    loginForm.addEventListener("submit", (e) => {
        e.preventDefault();

        const username = usernameInput.value.trim().toLowerCase();
        const password = passwordInput.value;

        const allUsers = JSON.parse(localStorage.getItem("pharmvault-users") || "{}");
        const userData = allUsers[username];

        if (!userData) {
            alert("Username not found. Please register an account.");
            return;
        }

        try {
            if (userData.encryptedNotes) {
                CryptoJS.AES.decrypt(userData.encryptedNotes, password).toString(CryptoJS.enc.Utf8);
            }
            sessionStorage.setItem("pharmvault-currentUser", username);
            sessionStorage.setItem("pharmvault-key", password);
            window.location.href = "app.html"; // Updated Link
        } catch (error) {
            alert("Incorrect password. Please try again.");
            passwordInput.value = "";
        }
    });
});