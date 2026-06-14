package com.leomadrassi.trollingfreedomreborn.other

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.nio.file.Paths

class DependencyChecker : Listener {

    companion object {
        @JvmStatic
        @Throws(java.io.IOException::class)
        fun DependencyChecker() {
            val path1 = Paths.get(Core.instance.server.worldContainer.absolutePath)
            val f = File("$path1/plugins/ProtocolLib.jar")

            if (Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
                Bukkit.getLogger().info("§3TF§8: §7Found ProtocolLib, all good")
            } else {
                if (!Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
                    f.createNewFile()
                    BufferedInputStream(URL("https://ci.dmulloy2.net/job/ProtocolLib/lastSuccessfulBuild/artifact/target/ProtocolLib.jar").openStream()).use { inputStream ->
                        FileOutputStream("$path1/plugins/ProtocolLib.jar").use { fileOS ->
                            val data = ByteArray(1024)
                            Bukkit.getLogger().info("§3TFR§8: §7ProtocolLib was missing so I downloaded it for you")
                            Bukkit.getLogger().info("§3TFR§8: §7§lRestart your server to get TrollingFreedom working")
                            Bukkit.getLogger().info("")
                            Bukkit.getLogger().info("")
                            Bukkit.getLogger().info("§3TFR§8: §7ProtocolLib was missing so I downloaded it for you")
                            Bukkit.getLogger().info("§3TFR§8: §7§lRestart your server to get TrollingFreedom working")
                            var byteContent: Int
                            while (inputStream.read(data, 0, 1024).also { byteContent = it } != -1) {
                                fileOS.write(data, 0, byteContent)
                            }
                        }
                    }
                }
            }
        }
    }
}
