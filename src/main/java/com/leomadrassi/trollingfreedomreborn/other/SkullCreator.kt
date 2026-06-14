package com.leomadrassi.trollingfreedomreborn.other

import com.cryptomorin.xseries.XMaterial
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.Skull
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.net.URI
import java.util.Base64
import java.util.UUID

object SkullCreator {

    @Deprecated("names don't make for good identifiers")
    fun itemFromName(name: String): ItemStack {
        val item = getPlayerSkullItem()
        return itemWithName(item, name)
    }

    @Deprecated("names don't make for good identifiers")
    fun itemWithName(item: ItemStack, name: String): ItemStack {
        requireNotNull(item) { "item should not be null!" }
        requireNotNull(name) { "name should not be null!" }

        val meta = item.itemMeta as SkullMeta
        meta.owningPlayer = Bukkit.getOfflinePlayer(name)
        item.setItemMeta(meta)
        return item
    }

    fun itemFromUuid(id: UUID): ItemStack {
        val item = getPlayerSkullItem()
        return itemWithUuid(item, id)
    }

    fun itemWithUuid(item: ItemStack, id: UUID): ItemStack {
        requireNotNull(item) { "item should not be null!" }
        requireNotNull(id) { "id should not be null!" }

        val meta = item.itemMeta as SkullMeta
        meta.owningPlayer = Bukkit.getOfflinePlayer(id)
        item.setItemMeta(meta)
        return item
    }

    fun itemFromUrl(url: String): ItemStack {
        val item = getPlayerSkullItem()
        return itemWithUrl(item, url)
    }

    fun itemWithUrl(item: ItemStack, url: String): ItemStack {
        requireNotNull(item) { "item should not be null!" }
        requireNotNull(url) { "url should not be null!" }

        return itemWithBase64(item, urlToBase64(url))
    }

    fun itemFromBase64(base64: String): ItemStack {
        val item = getPlayerSkullItem()
        return itemWithBase64(item, base64)
    }

    fun itemWithBase64(item: ItemStack, base64: String): ItemStack {
        requireNotNull(item) { "item should not be null!" }
        requireNotNull(base64) { "base64 should not be null!" }

        val meta = item.itemMeta as SkullMeta
        val hashAsId = UUID(base64.hashCode().toLong(), base64.hashCode().toLong())

        val profile = Bukkit.createProfile(hashAsId)
        profile.setProperty(com.destroystokyo.paper.profile.ProfileProperty("textures", base64))

        meta.setPlayerProfile(profile)
        item.setItemMeta(meta)
        return item
    }

    @Deprecated("names don't make for good identifiers")
    fun blockWithName(block: Block, name: String) {
        requireNotNull(block) { "block should not be null!" }
        requireNotNull(name) { "name should not be null!" }

        setBlockType(block)
        var skull = block.state as Skull
        skull.setOwningPlayer(Bukkit.getOfflinePlayer(name))
        skull.update()
    }

    fun blockWithUuid(block: Block, id: UUID) {
        requireNotNull(block) { "block should not be null!" }
        requireNotNull(id) { "id should not be null!" }

        setBlockType(block)
        var skull = block.state as Skull
        skull.setOwningPlayer(Bukkit.getOfflinePlayer(id))
        skull.update()
    }

    fun blockWithUrl(block: Block, url: String) {
        requireNotNull(block) { "block should not be null!" }
        requireNotNull(url) { "url should not be null!" }

        blockWithBase64(block, urlToBase64(url))
    }

    fun blockWithBase64(block: Block, base64: String) {
        requireNotNull(block) { "block should not be null!" }
        requireNotNull(base64) { "base64 should not be null!" }

        val hashAsId = UUID(base64.hashCode().toLong(), base64.hashCode().toLong())
        val args = String.format(
            "%d %d %d %s",
            block.x,
            block.y,
            block.z,
            "{Owner:{Id:\"$hashAsId\",Properties:{textures:[{Value:\"$base64\"}]}}}"
        )

        if (newerApi()) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "data merge block $args")
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "blockdata $args")
        }
    }

    private fun newerApi(): Boolean {
        return try {
            XMaterial.valueOf("PLAYER_HEAD")
            true
        } catch (_: IllegalArgumentException) {
            false
        }
    }

    private fun getPlayerSkullItem(): ItemStack {
        return if (newerApi()) {
            ItemStack(XMaterial.PLAYER_HEAD.parseMaterial()!!)
        } else {
            ItemStack(Material.PLAYER_HEAD, 1)
        }
    }

    private fun setBlockType(block: Block) {
        try {
            block.setType(XMaterial.PLAYER_HEAD.parseMaterial()!!, false)
        } catch (_: IllegalArgumentException) {
            block.setType(Material.PLAYER_HEAD, false)
        }
    }

    private fun urlToBase64(url: String): String {
        val actualUrl: URI = try {
            URI(url)
        } catch (e: java.net.URISyntaxException) {
            throw RuntimeException(e)
        }
        val toEncode = "{\"textures\":{\"SKIN\":{\"url\":\"$actualUrl\"}}}"
        return Base64.getEncoder().encodeToString(toEncode.toByteArray())
    }
}
