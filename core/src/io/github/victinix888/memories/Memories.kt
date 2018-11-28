package io.github.victinix888.memories

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.github.victinix888.memories.gamestate.GameState
import io.github.victinix888.memories.screen.ScreenMainMenu
import io.github.victinix888.memories.screen.ScreenOverworld

class Memories : Game() {

    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont
    lateinit var gameState: GameState

    /** Called when the [Application] is first created.  */
    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont()
        gameState = GameState()
        this.setScreen(ScreenOverworld(this))
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        this.screen.dispose()

        batch.dispose()
        font.dispose()
    }
}
