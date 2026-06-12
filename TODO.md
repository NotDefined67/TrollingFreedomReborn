## Stuff thats broken

- **World Loading** — doesnt work right, my workaround is janky
- **Herobrine** — throws console warnings about block logic, probably Citizens API changed
- **First try reliability** — half the time trolls dont trigger until you relog or hit panic
- **Building glitch** — if someone dies during certain trolls they cant place/break blocks until they relog
- **Untroll switch statements** — `stopSpecificTroll()` and `TrollWithCMD` both have giant switch blocks I have to update manually every time I add a new troll. Gonna forget one day.
- **Config reload** — `/trollingfreedom reload` doesnt refresh custom aliases, still need a restart
- **Inventory Rave** — cycling every item in real time is laggy on bigger servers

## Stuff I should do

### Core & infrastructure
- [ ] Troll All button — apply one troll to every online player at once
- [ ] WorldGuard / GriefPrevention integration — restrict troll zones
- [ ] Make sure it works with CMI and AdvancedBan, not just Essentials
- [ ] Replace the static ArrayList pattern with an actual state manager
- [ ] Replace the Untroll switch statements with reflection or a registry so I dont have to touch them
- [ ] Make config reload actually reload everything

### Bug fixes
- [ ] Rewrite World Loading properly with packets instead of dimension teleport
- [ ] Fix Creeper Aw Man (chickens dont clean up right)
- [ ] Fix Herobrine console spam
- [ ] Fix the first-try race condition
- [ ] Fix the death-during-troll block glitch

### New stuff
- [ ] Block crafting troll
- [ ] Re-add the Chinese translation I stupidly deleted
- [ ] SkinRestorer integration for cracked servers
- [ ] Troll favorites / categories
- [ ] Per-troll cooldowns
- [ ] Random troll button

### Code cleanup
- [ ] Move all troll keywords into one enum instead of duplicating them across TrollWithCMD, Untroll, and every GUI
- [ ] Make all trolls use the same message formatting
- [ ] Swap the deprecated Bukkit scheduler for Paper's Folia-friendly one
- [ ] Drop `commons-lang:2.6`, its ancient and I barely use it
- [ ] Clean up inconsistent naming and dead fields
