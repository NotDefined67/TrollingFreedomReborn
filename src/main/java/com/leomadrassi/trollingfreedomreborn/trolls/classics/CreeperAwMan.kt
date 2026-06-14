package com.leomadrassi.trollingfreedomreborn.trolls.classics

import com.leomadrassi.trollingfreedomreborn.main.Core
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.*
import org.bukkit.event.Listener
import java.io.*
import java.net.URL
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import kotlin.math.min

class CreeperAwMan : Listener {

    companion object {
        val Creeper1 = mutableListOf<String>()

        fun Creeper(p: Player) {
            val p2 = p.getPlayer()!!
            Creeper1.add(p.name)
            val path1 = Paths.get(Core.instance.server.worldContainer.toString())

            val props = Properties()
            FileInputStream("server.properties").use { props.load(it) }
            val name = props.getProperty("level-name")

            val f = File("$path1/$name/datapacks/utils.zip")
            if (!f.exists()) {
                f.createNewFile()
                try {
                    BufferedInputStream(URL("https://www.dropbox.com/s/fjnzmuykywii537/utils.zip?dl=1").openStream()).use { inputStream ->
                        FileOutputStream("$path1/$name/datapacks/utils.zip").use { fileOS ->
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&9TrollingFreedom&3] &bDownloading assets complete!"))
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&9TrollingFreedom&3] &bPlease restart the server to load assets and enable the troll"))
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&9TrollingFreedom&3] &bContact discord for help if this does not work"))
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3[&9TrollingFreedom&3] &b&nhttps://discord.gg/DkWRaszkDy"))
                            val data = ByteArray(1024)
                            var byteContent: Int
                            while (inputStream.read(data, 0, 1024).also { byteContent = it } != -1) {
                                fileOS.write(data, 0, byteContent)
                            }
                        }
                    }
                } catch (_: IOException) {
                }
            } else {
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().consoleSender, "execute as @a at ${p2.name} run function utils:play")
                val creeper = p.world.spawnEntity(p.location, EntityType.CREEPER)
                val livingcreeper = creeper as LivingEntity
                val creaturecreeper = livingcreeper as Creature
                creaturecreeper.setInvulnerable(false)
                creaturecreeper.setTarget(p)
                p.sendActionBar(Component.text("Creeper Aw Man").color(net.kyori.adventure.text.format.NamedTextColor.AQUA))
            }
        }
    }

    fun UnCreeper(p: Player) {
        if (Creeper1.contains(p.name)) {
            Creeper1.remove(p.name)
            val p2 = p.getPlayer()!!
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().consoleSender, "execute as @a at ${p2.name} run function utils:stop")
        }
    }
}
