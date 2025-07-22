# 🇫🇷 FrenchDiscordPlugin

A custom lightweight plugin designed for the [French Discord](https://discord.gg/baguettes) community Minecraft survival server.  
Focuses on balanced gameplay, in-game unlockables, and seamless Discord integration.  
Supports **Java + Bedrock**, **cracked accounts**, and a **progressive in-game economy**!

---

## 🚀 Features

✅ No dependency on Essentials / CMI  
✅ Lightweight, performant and clean codebase  
✅ Works on both Java and Bedrock (via Geyser)  
✅ Commands gated by permission and in-game progression  
✅ In-game `/boutique` GUI shop (uses Totems, Diamonds, Emerald blocks, Redstone...)  
✅ Player rank promotion with `/promoteplayer`  
✅ KeepInventory for specific players via permission  
✅ Homes and teleport system with permission-based limits  
✅ Fully configurable and open source  
✅ Made with ❤️ for the community

---

## 🎮 Public Commands (for Players)

| Command         | Status   | Description                               | Unlock via Boutique       |
|-----------------|----------|-------------------------------------------|----------------------------|
| `/discord`      | 🟢       | Show the Discord invite                   | ❌ (public)                |
| `/site`         | 🟢       | Show the website                          | ❌ (public)                |
| `/rules`        | 🟢       | Show the server rules                     | ❌ (public)                |
| `/help`         | 🟢       | List available commands                   | ❌ (public)                |
| `/spawn`        | 🟢       | Teleport to spawn                         | ✅ 10 diamonds             |
| `/back`         | 🟢       | Teleport to last location                 | ✅ 5 stacks redstone       |
| `/home`         | 🟢       | Teleport to a saved home                  | ✅ home level ≥ 1          |
| `/sethome`      | 🟢       | Set a home                                | ✅ home level ≥ 1          |
| `/delhome`      | 🟢       | Delete a saved home                       | ✅ home level ≥ 1          |
| `/heal`         | 🟢       | Heal yourself                             | ✅ 35 totems               |
| `/feed`         | 🟢       | Refill hunger                             | ✅ 32 steaks               |
| `/ec`           | 🟢       | Open Ender Chest                          | ✅ 16 blocks of obsidian   |
| `/craft`        | 🟢       | Open a crafting table                     | ✅ 32 wooden planks        |
| `/furnace`      | 🟡       | Open a virtual furnace                    | ✅ 32 cobblestone          |
| `/anvil`        | 🟢       | Open a virtual anvil                      | ✅ 20 iron blocks          |
| `/compress`     | 🔴       | Compress items into blocks automatically  | ❌ (not yet coded)         |

---

## 🛒 In-Game Shop

Use `/boutique` to open a GUI-based shop where players trade **resources** for **permissions**.  
No pay-to-win. Just play, collect, and unlock!

---

## 🛠️ Staff Commands

| Command              | Status | Description                                           | Permission                     |
|----------------------|--------|-------------------------------------------------------|--------------------------------|
| `/promoteplayer`     | 🟢     | Promote a player from visitor to regular player       | `frenchdiscord.staff`          |
| `/pluginupdate`      | 🟢     | Auto-download latest plugin version from GitHub       | `frenchdiscord.staff`          |
| `/gmc`, `/gms`, etc. | 🟢     | Gamemode switcher for staff                           | `frenchdiscord.staff.gamemode` |

---

## 📂 Permissions Overview

| Permission                          | Description                               | Required For       |
|-------------------------------------|-------------------------------------------|--------------------|
| `frenchdiscord.visiteur`           | Visitor status (no pickup, godmode)       | ✅ Auto on join     |
| `frenchdiscord.discord`            | Access to `/discord`                      | ✅ Public           |
| `frenchdiscord.site`               | Access to `/site`                         | ✅ Public           |
| `frenchdiscord.rules`              | Access to `/rules`                        | ✅ Public           |
| `frenchdiscord.help`               | Access to `/help`                         | ✅ Public           |
| `frenchdiscord.spawn`              | Access to `/spawn`                        | ✅ Shop             |
| `frenchdiscord.back`               | Access to `/back`                         | ✅ Shop             |
| `frenchdiscord.keepinv`            | Keep inventory on death                   | ✅ Shop             |
| `frenchdiscord.home`               | Access to `/home`                         | ✅ Shop             |
| `frenchdiscord.sethome`            | Access to `/sethome`                      | ✅ Shop             |
| `frenchdiscord.delhome`            | Access to `/delhome`                      | ✅ Shop             |
| `frenchdiscord.home.max.1 → 10`    | Number of homes allowed                   | ✅ Shop             |
| `frenchdiscord.heal`               | Use `/heal`                               | ✅ Shop             |
| `frenchdiscord.feed`               | Use `/feed`                               | ✅ Shop             |
| `frenchdiscord.ec`                 | Use `/ec`                                 | ✅ Shop             |
| `frenchdiscord.craft`              | Use `/craft`                              | ✅ Shop             |
| `frenchdiscord.furnace`            | Use `/furnace`                            | 🟡 Partial          |
| `frenchdiscord.anvil`              | Use `/anvil`                              | ✅ Shop             |
| `frenchdiscord.compress`           | Use `/compress`                           | 🔴 Not yet          |
| `frenchdiscord.staff`              | Access to promote/update commands         | ✅ Manual Staff     |
| `frenchdiscord.staff.gamemode`     | Use `/gmc`, `/gms`, etc.                  | ✅ Manual Staff     |

---

## 📦 Requirements

- Minecraft 1.21+  
- Java 21  
- Spigot / Paper / Purpur  
- LuckPerms  
- (Optional) PlugManX for plugin reload  
- (Optional) DiscordSRV for Discord integration

---

## 👥 Credits

Maintained by [JM_command](https://github.com/JM-command)  
Plugin made for the [French Discord](https://discord.gg/baguettes) community server ❤️  

---

## 🛠 Contributing

This is an open-source, community-driven project — and **you can help!** 🚀  
Fork the repo, make your changes, and open a pull request.  
All contributions are welcome, whether it's code, ideas, or feedback 🙏

👉 See [`CONTRIBUTING.md`](./CONTRIBUTING.md) to get started.

---

## 📘 License

This project is open-source under the MIT License.
