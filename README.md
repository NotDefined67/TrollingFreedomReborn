# TrollingFreedomReborn 🎭

**TrollingFreedomReborn** is a powerful, modernized Minecraft troll plugin featuring a comprehensive GUI with over 70
unique, hilarious, and surprising trolls to prank your friends. This is a high-performance, updated fork of the original
TrollingFreedom project.

**Fully supported version:** 1.21.11  
**Versions that may work:** 1.14 and above

* For versions 1.20.5 and before, use [TrollingFreedom_Continue](https://github.com/Lumine1909/TrollingFreedom_Continue)
* For version 1.19 and before use [iangry0/TrollingFreedom](https://github.com/iangry0/TrollingFreedom)
* Original plugin
  link: [SpigotMC - TrollingFreedom](https://www.spigotmc.org/resources/%E2%AD%90-trollingfreedom-%E2%AD%90-1-free-troll-plugin-with-gui.53419/)

---

## 🚀 Features

### 🛠️ General

* **Highly Customizable:** Tweak almost every feature via the `config.yml`.
* **Language Support:** Removed Chinese support from original for the sake of my insanity.
* **Safety First:** Includes a **Blocklist** to protect specific players (like Admins) from being trolled.
* **Modernized Code:** Refactored for stability on 1.18 - 1.21.11+.

### 📦 Dependencies

* **ProtocolLib** (Required — auto-downloaded if missing)
* **EssentialsX** (Optional — enables nickname troll)
* **Citizens2** (Optional — required only for the Herobrine troll)
* **SkinRestorer** (Optional — better experience on cracked servers)

### 🖱️ The Troll Menu

Open the main interface with `/trollgui` to access over 70 unique actions:

* **Classic Trolls:** Herobrine (NPC), Fake Ban, Fake Crash, OP Troll, and TNT Troll.
* **Interactive Trolls:** Control player movement, Inventory See, and Spin.
* **Visual/Audio Pranks:** Ghast Screams, Guardian Hallucinations, Pumpkin Head, and Flash Screen.
* **Chaos:** Nuke Troll, Coffin Dance (with music!), Rocket Launch, and Rain Items.
* **World & Bed:** Prevent Sleep, World Loading fake-out, and "Monsters Nearby" messages.

---

## 📸 Screenshots

![Troll Demo](screenshots/1.png)
![Troll Demo](screenshots/2.png)
![Troll Demo](screenshots/3.png)
![Troll Demo](screenshots/4.png)
![Troll Demo](screenshots/5.png)

### Menu

![Troll Demo](screenshots/ui.gif)
*The interactive GUI makes trolling easy and fast.*

---

## 💻 Commands & Permissions

| Command                               | Description                                              | Permission                    |
|:--------------------------------------|:---------------------------------------------------------|:------------------------------|
| `/troll`                              | Opens the main Troll GUI                                 | `trollingfreedom.open`        |
| `/trollgui`                           | Alias for `/troll`                                       | `trollingfreedom.open`        |
| `/trollf <player> <troll>`            | Execute a troll via command                              | `trollingfreedom.trollf`      |
| `/untroll <player> [troll]`           | Remove all (or specific) active trolls                   | `trollingfreedom.untroll`     |
| `/untroll all`                        | Remove ALL trolls from ALL players                       | `trollingfreedom.untroll`     |
| `/panicstoptroll`                     | Nuclear reset — cancels all plugin tasks                 | `trollingfreedom.panic`       |
| `/trollingfreedom`                    | Show help page                                           | —                             |
| `/trollingfreedom reload`             | Reload plugin configuration                              | `trollingfreedom.reload`      |
| `/troll reload`                       | Open settings menu (reload config)                       | `trollingfreedom.reload`      |
| `/troll add-blocked <player>`         | Add player to blocklist                                   | `trollingfreedom.open`        |
| `/troll remove-blocked <player>`      | Remove player from blocklist                              | `trollingfreedom.open`        |
| `/troll toggle-troll-op`              | Toggle whether OPs can be trolled                        | `trollingfreedom.open`        |
| `/troll giveskull`                    | Get the TrollingFreedom skull item                       | `trollingfreedom.open`        |
| `/troll help` / `/troll contact`      | Show help / contact info                                 | `trollingfreedom.open`        |
| `/control <player>`                   | Take control of a player's movement/chat                 | `trollingfreedom.trollf`      |

**Master Permission:** `trollingfreedom.*`

---

## Config

Everything is in `src/main/resources/config.yml`. Open it and look — the comments explain most things.

Main things you'd want to change:

- **Blocklist** — Add or remove player names under `blocklist:`. Anyone in this list cant be trolled.
- **allow-troll-op: true/false** — Set to `false` if you dont want ops to be trolled.
- **untroll-on-quit: true/false** — Auto untroll players when they leave.
- **custom-aliases** — Uncomment to add extra command aliases for `/trollf` and `/trollgui`.
- **troll-config.randomchat** — The random messages the chat troll pulls from. Swap em with your own.
- **Items** — Every trolls name and lore in the GUI is configurable under `items:`.

Everything else in there is chat messages and GUI strings. Tweak whatever you want.

---

## 📜 Credits & History

This project is a **Reborn** version of the original "TrollingFreedom" plugin, maintained to keep the fun alive on
modern Minecraft versions.

* **Current Developer:** [Leo Madrassi](https://github.com/leomadrassidev)
* **Forked From:** [Lumine1909's TrollingFreedom_Continue](https://github.com/Lumine1909/TrollingFreedom_Continue)
* **Original Author:** [iAngry0 (Original TrollingFreedom)](https://github.com/iangry0/TrollingFreedom)
* **AI Assistance:** Credits to **Gemini Flash** for logic generation and refactoring
* **Translations:** Special thanks to **Cha_Shao** for the original Chinese translation

---

## ⚖️ License

This project is open-source and licensed under the **GNU General Public License v3.0**. You must provide attribution to
the original authors and keep derivative works under the same license.

You are welcome to fork and take the project from here on as long as you give the proper credits to me and other
authors (iangry0 & Lumine1909).

---

## 🔧 CHANGELOG

[CHANGELOG](CHANGELOG.md)

## 🔧 Maintenance Note

I decided to fork and maintain this when I needed to use it myself. I'll be honest: I don't understand half the things
in this plugin! I haven't coded in a long time, so a significant portion of the logic was AI-generated.

The code is still quite messy—it needs a braver person to fix it properly. Feel free to contribute; credits will be
given below.

---

## 📝 Roadmap , Known Issues & TODO

[TODO](TODO.md)

## 🛠️ Support & Links

* **Source Code:** [GitHub Repository](https://github.com/leomadrassidev/TrollingFreedomReborn)
* **Report Bugs:** [GitHub Issues](https://github.com/leomadrassidev/TrollingFreedomReborn/issues)
* **Developer Discord:** `uball`

> [!NOTE]
> Feel free to open an issue or ping me on Discord if I missed an update or you have a suggestion. If you report a bug
> or suggest a feature that gets implemented, you will be credited in the next commit!

## 🔧 Full Troll Reference

The plugin includes **82+ trolls** across 9 categories:

| Category     | Trolls                                                                                       |
|:-------------|:---------------------------------------------------------------------------------------------|
| Classic      | Herobrine, OP Trick, Pumpkin, RickRoll, Slenderman, Spin, Creeper Aw Man, Anvil Drop, Coffin |
| Chat         | Random Chat, Deafen, Explode On Chat, Nick, Reverse Message                                  |
| Movement     | AFK, Cage, Control, Force Jump, Free Fall, Freeze, Invert Walk, Lag, Launch, Lightning, Sneak Destroy |
| Explosion    | Exploding Chicken, Explosive Sheep, Kitty Cannon, Snowman, Nuke, TNT Place                   |
| Inventory    | Drop All, Inventory Rave, Inventory Stop, Invsee, Lock Inventory, Random Inventory           |
| Beds         | Bed Explosion, Bed Missing, Bed Night, Stop Sleep                                            |
| Packet       | Credits, Demo, Guardian, World Loading                                                       |
| Fake Stuff   | Fake Kicks (Crash/Close/Ban), Fake Reload                                                    |
| Random       | Annoy, Aquaphobia, Break, Burn, Entity Multiply, Hide Players, InstaTool Break, Poop, Potato, Rain Items, Random Crafts, Random Particle, Random TP, Ring of Fire, Silverfish, Slippery Hands, Sounds, Starve, Time Flash, Void, Vomit, All Entities Die |
