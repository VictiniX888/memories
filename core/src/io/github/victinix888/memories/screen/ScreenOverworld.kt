package io.github.victinix888.memories.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import io.github.victinix888.memories.Memories
import ktx.graphics.use

class ScreenOverworld(val game: Memories) : Screen {

    private val map = Texture(Gdx.files.internal("map.png"))
    private val playerSprite = Texture(Gdx.files.internal("player.png"))
    private val player = Rectangle(0f, 0f, 8f, 8f)
    private val camera = OrthographicCamera()

    init {
        camera.setToOrtho(false, 800f, 480f)
    }

    /** Called when this screen is no longer the current screen for a [Game].  */
    override fun hide() {
    }

    /** Called when this screen becomes the current screen for a [Game].  */
    override fun show() {
    }

    /** Called when the screen should render itself.
     * @param delta The time in seconds since the last render.
     */
    override fun render(delta: Float) {
        // set background color
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()
        // tell the SpriteBatch to render in the coordinate system specified by the camera.
        game.batch.projectionMatrix = camera.combined

        game.batch.use {
            it.draw(map, 0f, 0f)
            it.draw(playerSprite, player.x, player.y)
        }

        //handle movement
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.x -= 200*delta
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.x += 200*delta
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.y -= 200*delta
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.y += 200*delta
        }

        //prevent player moving offscreen
        if (player.x < 0f) {
            player.x = 0f
        } else if (player.x >= 800f - player.width) {
            player.x = 800f - player.width
        }
        if (player.y < 0f) {
            player.y = 0f
        } else if (player.y >= 480f - player.width) {
            player.y = 480f - player.width
        }
    }

    /** @see ApplicationListener.pause
     */
    override fun pause() {
    }

    /** @see ApplicationListener.resume
     */
    override fun resume() {
    }

    /** @see ApplicationListener.resize
     */
    override fun resize(width: Int, height: Int) {
    }

    /** Called when this screen should release all resources.  */
    override fun dispose() {
        map.dispose()
        playerSprite.dispose()
    }
}