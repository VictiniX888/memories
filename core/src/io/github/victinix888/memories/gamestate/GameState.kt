package io.github.victinix888.memories.gamestate

import io.github.victinix888.memories.coordinate.Coordinate

class GameState(savefile: IntArray = IntArray(0)) {

    lateinit var playerCoordinate: Coordinate

    init {

        if (savefile.isEmpty()) {
            playerCoordinate = Coordinate(0, 0)
            playerCoordinate.x = 5
        }
        else {
            //if there is a save loaded
        }
    }
}