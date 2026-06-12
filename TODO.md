## Roadmap & Known Issues

### Known Issues

- **World Loading:** Doesn't work correctly on Paper 1.21.11; dimension teleport workaround is unreliable
- **Herobrine:** Block interaction logic causes console warnings (likely Citizens2 API changes)
- **Reliability:** Trolls sometimes fail to trigger on first attempt; `/panicstoptroll` or relog usually fixes it
- **Building Glitch:** Dying during certain trolls can permanently block block break/place until relog
- **Untroll Switch Statement:** `UnTroll.stopSpecificTroll()` and `TrollWithCMD` both use large switch statements that must be manually updated when adding new trolls — easy to miss
- **Config Reload:** `/trollingfreedom reload` doesn't fully refresh all runtime state (custom aliases need server restart)
- **Inventory Rave Performance:** Rainbow cycling all inventory items in real-time is expensive — lags on larger servers

### To-Do

#### Core & Infrastructure
- [ ] Add a "Troll All" feature (apply one troll to all online players)
- [ ] Add WorldGuard / GriefPrevention / Claims integration to restrict troll zones
- [ ] Ensure compatibility with CMI, AdvancedBan, and other major server plugins
- [ ] Refactor troll state into a centralized manager (replace static ArrayList pattern)
- [ ] Replace Untroll switch statements with reflection or a registry pattern
- [ ] Fully reloadable config without server restart

#### Bug Fixes
- [ ] Fix the World Loading system properly (packet-based, not dimension teleport)
- [ ] Fix Creeper Aw Man (entity spawning timing / cleanup)
- [ ] Fix Herobrine block interaction console warnings
- [ ] Fix first-try reliability issue (task registration race condition?)
- [ ] Fix building glitch on death during active trolls

#### New Trolls & Features
- [ ] Block crafting troll (randomize or block crafting table usage)
- [ ] Re-add Chinese translation from original plugin
- [ ] Integration with SkinRestorer for better cracked-server experience
- [ ] Troll categories / favorites system
- [ ] Per-troll cooldowns per player
- [ ] Random troll button (pick a random troll for a player)

#### Code Quality
- [ ] Move troll keywords into a shared constant enum (used by TrollWithCMD, Untroll, and GUI)
- [ ] Add unified message formatting so all trolls use consistent messaging
- [ ] Replace deprecated Bukkit scheduler with Paper's Folia-friendly scheduler
- [ ] Remove redundant `commons-lang:2.6` dependency (use modern Java equivalents)
- [ ] Clean up AI-generated code patterns (inconsistent naming, unused fields)
