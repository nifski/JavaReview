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
    }

    themeToggleButton.addEventListener('click', () => {
        let newTheme;
        if (body.classList.contains('dark-mode')) {
            newTheme = 'light';
        } else {
            newTheme = 'dark';
        }
        localStorage.setItem('notes-theme', newTheme);
        applyTheme(newTheme);
    });
});