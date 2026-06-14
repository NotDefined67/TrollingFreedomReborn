package com.leomadrassi.trollingfreedomreborn.trolls.classics

import com.leomadrassi.trollingfreedomreborn.main.Core
import org.bukkit.Bukkit
import org.bukkit.Instrument
import org.bukkit.Material
import org.bukkit.Note
import org.bukkit.Note.Tone
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta

class RickRoll : Listener {

    companion object {
        val Rick1 = mutableListOf<String>()
    }

    var procedure = 0

    fun RickRoll(p: Player) {
        Rick1.add(p.name)

        val pages = mutableListOf(
            "§4Never gonna give you up\nNever gonna let you down\nNever gonna run around and desert you\nNever gonna make you cry\nNever gonna say goodbye\nNever gonna tell a lie and hurt you",
            "We've known each other for so long\nYour heart's been aching but you're too shy to say it\nInside we both know what's been going on\nWe know the game and we're gonna play it",
            "And if you ask me how I'm feeling\nDon't tell me you're too blind to see",
            "Never gonna give you up\nNever gonna let you down\nNever gonna run around and desert you\nNever gonna make you cry\nNever gonna say goodbye",
            "Never gonna tell a lie and hurt you\nNever gonna give you up\nNever gonna let you down\nNever gonna run around and desert you\nNever gonna make you cry\nNever gonna say goodbye\nNever gonna tell a lie and hurt you",
            "Never gonna give, never gonna give\n(Give you up)\n(Ooh) Never gonna give, never gonna give\n(Give you up)",
            "We've known each other for so long\nYour heart's been aching but you're too shy to say it\nInside we both know what's been going on\nWe know the game and we're gonna play it",
            "I just wanna tell you how I'm feeling\nGotta make you understand",
            "Never gonna give you up\nNever gonna let you down\nNever gonna run around and desert you\nNever gonna make you cry\nNever gonna say goodbye\nNever gonna tell a lie and hurt you\nNever gonna give you up",
            "Never gonna let you down\nNever gonna run around and desert you\nNever gonna make you cry\nNever gonna say goodbye\nNever gonna tell a lie and hurt you\nNever gonna give you up\nNever gonna let you down\nNever gonna run around and desert you\nNever gonna make you cry"
        )

        val writtenBook = ItemStack(Material.WRITTEN_BOOK, 1)
        val bookMeta = writtenBook.itemMeta as BookMeta
        bookMeta.setTitle("Never Gonna Give You Up")
        bookMeta.setAuthor("Rick Astley")
        bookMeta.setPages(pages)
        writtenBook.itemMeta = bookMeta

        p.openBook(writtenBook)

        val id = Bukkit.getServer().scheduler.scheduleSyncRepeatingTask(Core.instance, Runnable {
            when (procedure % 56) {
                0 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.A))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(0, Tone.G))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(0, Tone.D))
                }
                2 -> {
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(0, Tone.D))
                }
                3 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.natural(0, Tone.F))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.G))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.G))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(0, Tone.D))
                }
                4 -> {
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.A))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.A))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(0, Tone.D))
                }
                5 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.natural(0, Tone.G))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.C))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.C))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(0, Tone.D))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.C))
                }
                6 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.natural(0, Tone.F))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.A))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.A))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(0, Tone.D))
                }
                8 -> {
                    p.playNote(p.location, Instrument.PIANO, Note.natural(0, Tone.F))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(0, Tone.F))
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.D))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.F))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.C))
                    p.playNote(p.location, Instrument.FLUTE, Note.natural(0, Tone.F))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.BASS_DRUM, Note.natural(0, Tone.C))
                }
                10 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.D))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                }
                11 -> {
                    p.playNote(p.location, Instrument.BASS_DRUM, Note.sharp(0, Tone.D))
                    p.playNote(p.location, Instrument.FLUTE, Note.natural(0, Tone.F))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.STICKS, Note.natural(0, Tone.D))
                }
                12 -> {
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(0, Tone.D))
                }
                13 -> p.playNote(p.location, Instrument.BASS_GUITAR, Note.natural(0, Tone.C))
                14 -> {
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(0, Tone.D))
                    p.playNote(p.location, Instrument.PIANO, Note.natural(0, Tone.C))
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.A))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.G))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.D))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.D))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.STICKS, Note.sharp(0, Tone.G))
                }
                15 -> p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                16 -> {
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(0, Tone.G))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.BASS_DRUM, Note.natural(0, Tone.C))
                }
                18 -> {
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(0, Tone.G))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                }
                19 -> {
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.STICKS, Note.natural(0, Tone.D))
                }
                20 -> {
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.G))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(0, Tone.D))
                }
                21 -> p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.A))
                22 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.D))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.G))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.C))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.STICKS, Note.sharp(0, Tone.G))
                }
                23 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.G))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.A))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                }
                24 -> {
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(0, Tone.G))
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.natural(0, Tone.F))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.D))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.D))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.BASS_DRUM, Note.natural(0, Tone.C))
                }
                26 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.natural(0, Tone.F))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                }
                27 -> {
                    p.playNote(p.location, Instrument.BASS_DRUM, Note.natural(0, Tone.C))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.D))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.STICKS, Note.natural(0, Tone.D))
                }
                28 -> {
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(0, Tone.D))
                }
                29 -> {
                    p.playNote(p.location, Instrument.PIANO, Note.natural(0, Tone.F))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(0, Tone.A))
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.A))
                    p.playNote(p.location, Instrument.PIANO, Note.natural(1, Tone.F))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.C))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.STICKS, Note.sharp(0, Tone.G))
                }
                30 -> p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                31 -> {
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.BASS_DRUM, Note.natural(0, Tone.C))
                }
                32 -> p.playNote(p.location, Instrument.FLUTE, Note.natural(0, Tone.C))
                33 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.A))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.A))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                }
                34 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.A))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.STICKS, Note.natural(0, Tone.D))
                }
                35 -> {
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.G))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.G))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(0, Tone.D))
                }
                36 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.natural(0, Tone.C))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.F))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.A))
                }
                37 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.A))
                    p.playNote(p.location, Instrument.PIANO, Note.natural(1, Tone.F))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.C))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.C))
                    p.playNote(p.location, Instrument.STICKS, Note.sharp(0, Tone.G))
                }
                38 -> {
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.C))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.A))
                }
                39 -> {
                    p.playNote(p.location, Instrument.PIANO, Note.natural(0, Tone.F))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(0, Tone.F))
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.D))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(0, Tone.F))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.C))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.BASS_DRUM, Note.natural(0, Tone.C))
                }
                40 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(1, Tone.D))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                }
                41 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.natural(0, Tone.C))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.STICKS, Note.natural(0, Tone.D))
                }
                42 -> {
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.D))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(0, Tone.D))
                }
                43 -> p.playNote(p.location, Instrument.BASS_GUITAR, Note.natural(0, Tone.C))
                44 -> {
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(0, Tone.D))
                    p.playNote(p.location, Instrument.PIANO, Note.natural(0, Tone.C))
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.A))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(0, Tone.G))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.D))
                    p.playNote(p.location, Instrument.FLUTE, Note.natural(0, Tone.C))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.STICKS, Note.sharp(0, Tone.G))
                }
                45 -> p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                46 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.G))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.BASS_DRUM, Note.natural(0, Tone.C))
                }
                47 -> p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.A))
                48 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.G))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.G))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                }
                49 -> {
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.STICKS, Note.natural(0, Tone.D))
                }
                50 -> {
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(1, Tone.G))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(0, Tone.D))
                }
                51 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.D))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.G))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.STICKS, Note.sharp(0, Tone.G))
                }
                52 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.sharp(0, Tone.D))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                }
                53 -> {
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(0, Tone.G))
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.natural(0, Tone.F))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.D))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.BASS_DRUM, Note.natural(0, Tone.C))
                }
                54 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.natural(0, Tone.F))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.C))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                }
                55 -> {
                    p.playNote(p.location, Instrument.BASS_GUITAR, Note.natural(0, Tone.F))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.STICKS, Note.natural(0, Tone.D))
                }
                56 -> {
                    p.playNote(p.location, Instrument.PIANO, Note.natural(0, Tone.F))
                    p.playNote(p.location, Instrument.PIANO, Note.sharp(0, Tone.C))
                    p.playNote(p.location, Instrument.FLUTE, Note.sharp(0, Tone.C))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(1, Tone.E))
                    p.playNote(p.location, Instrument.SNARE_DRUM, Note.natural(0, Tone.D))
                }
            }
            procedure++
        }, 5L, 3L)
        Core.instance.addTask(p, "rickroll", id)
    }

    fun UnRickRoll(p: Player) {
        val p2 = p.name
        Rick1.remove(p.name)
    }

    @EventHandler
    fun onquit(e: PlayerQuitEvent) {
        val p = e.player
        if (Rick1.contains(p.name)) {
            Rick1.remove(p.name)
        }
    }

    @EventHandler
    fun onexit(e: PlayerMoveEvent) {
        val p = e.player
        if (Rick1.contains(p.name)) {
            Rick1.remove(p.name)
        }
    }
}
