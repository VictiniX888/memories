package io.github.victinix888.memories.entity

import io.github.victinix888.memories.coordinate.Coordinate
import io.github.victinix888.memories.coordinate.Direction

open class Entity(var coords: Coordinate) {

    fun move(direction: Direction) {
        when (direction) {
            Direction.UP    -> coords.y++
            Direction.DOWN  -> coords.y--
            Direction.RIGHT -> coords.x++
            Direction.LEFT  -> coords.x--
        }
    }
}