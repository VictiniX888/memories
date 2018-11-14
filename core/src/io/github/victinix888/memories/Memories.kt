package io.github.victinix888.memories

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import io.github.victinix888.memories.screen.ScreenMainMenu

class Memories : Game() {

    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont

    /** Called when the [Application] is first created.  */
    override fun create() {
        batch = SpriteBatch()
        font = BitmapFont()
        this.setScreen(ScreenMainMenu(this))
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
