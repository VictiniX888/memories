package io.github.victinix888.memories.gamestate

import io.github.victinix888.memories.coordinate.Coordinate
import io.github.victinix888.memories.entity.EntityPlayer

class GameState(savefile: IntArray = IntArray(0)) {

    lateinit var player: EntityPlayer

    init {

        if (savefile.isEmpty()) {
            player = EntityPlayer()
        }
        else {
            //if there is a save loaded
        }
    }
}