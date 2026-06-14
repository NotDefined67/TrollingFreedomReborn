package com.leomadrassi.trollingfreedomreborn.other

import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import java.net.URL
import java.util.Scanner
import java.util.function.Consumer

class UpdateChecker(private val plugin: Plugin, private val resourceId: Int) {

    fun getVersion(consumer: Consumer<String>) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, Runnable {
            try {
                URL("https://api.spigotmc.org/legacy/update.php?resource=$resourceId").openStream().use { inputStream ->
                    Scanner(inputStream).use { scanner ->
                        if (scanner.hasNext()) {
                            consumer.accept(scanner.next())
                        }
                    }
                }
            } catch (exception: Exception) {
                this.plugin.logger.info("Cannot look for updates: ${exception.message}")
            }
        })
    }
}
