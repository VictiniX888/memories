package io.github.victinix888.memories.map

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.maps.MapLayers
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile

fun loadMap(tilesheet: Texture): TiledMap {
    //replace this with loading files with instructions on what tiles to place where instead of having the instructions here

    //splits tilesheet into 16x16 tiles
    val tiles: Array<Array<TextureRegion>> = TextureRegion.split(tilesheet, 16, 16)
    val map: TiledMap = TiledMap()
    val layers: MapLayers = map.layers

    //create a maplayer (32x32 tiles) and fill with alternating 1st and 2nd tile
    val layer: TiledMapTileLayer = TiledMapTileLayer(32, 32, 16, 16)
    for (x in 0 until layer.width) {
        for (y in 0 until layer.height) {
            val tileNumber: Int = (x + y) % 2
            val cell: Cell = Cell()
            cell.tile = StaticTiledMapTile(tiles[0][tileNumber])
            layer.setCell(x, y, cell)
        }
    }

    layers.add(layer)

    return map
}