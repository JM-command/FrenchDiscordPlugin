# 👥 Contributing to FrenchDiscordPlugin

Hi there! 👋  
Thank you for considering contributing to this project.  
This plugin is made **by the community, for the community** — and every contribution counts! 💜

---

## 📦 What is this project?

**FrenchDiscordPlugin** is a lightweight Minecraft plugin tailored for survival servers, with features like `/boutique` item-based shops, permission-based progression, and a visitor → player promotion system.  
It replaces Essentials/CMI with a clean, modular, open-source alternative.

---

## ⚙️ Setup & Requirements

Before contributing, make sure you have:

- **Java 21**
- A Minecraft server (Spigot, Paper, or Purpur) running 1.21.7+
- [LuckPerms](https://luckperms.net/) installed (required)

To test the plugin locally:

1. Compile the plugin using Maven:
   - `mvn clean package`
2. Copy the generated `.jar` from `target/` into your local server’s `plugins/` folder
3. Restart your server and test your changes

---

## 🚀 How to contribute

### 1. Fork this repository

Click the **"Fork"** button at the top right of the GitHub page.

### 2. Clone your fork

`git clone https://github.com/YOUR_USERNAME/FrenchDiscordPlugin.git`

Then:

`cd FrenchDiscordPlugin`

### 3. Create a new branch

`git checkout -b feature/my-new-feature`

### 4. Make your changes

- Write clean, readable code (Java 21)
- Follow the plugin’s structure and logic
- Test your feature before pushing

### 5. Commit your changes

Use clear commit messages like:

- `feat: added /repair command`
- `fix: permission bug in /home`
- `docs: updated README for /furnace`

### 6. Push and open a pull request

- `git push origin feature/my-new-feature`
- Go to GitHub → your fork → click **"New pull request"**
- Describe what your change does and why

---

## ✅ What you can work on

- New GUI items or permissions in `/boutique`
- Commands: `/repair`, `/workbench`, `/compress`, etc.
- Fix bugs, improve performance or readability
- Improve documentation (README, CONTRIBUTING, etc.)

---

## 🚫 Please avoid

- Real-money / donation systems
- Massive feature drops in one PR (split them)
- Obfuscated code or unreadable logic
- Copy-pasting features from Essentials/CMI

---

## 🧠 Tips

- Keep changes modular
- Write comments where helpful
- Open an Issue first if unsure
- Use English / French in code and messages

---

## 💬 Need help?

- Open an Issue or Discussion on this repository
- Or contact [JM_command](https://github.com/JM-command) directly

Thanks again for contributing — let's build something awesome together! 🧡
