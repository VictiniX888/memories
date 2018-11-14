package io.github.victinix888.memories.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import io.github.victinix888.memories.Memories
import ktx.graphics.use

class ScreenMainMenu(val game: Memories) : Screen {

    private var camera: OrthographicCamera

    init {

        camera = OrthographicCamera()
        camera.setToOrtho(false, 800f, 480f)
    }

    /** Called when this screen becomes the current screen for a [Game].  */
    override fun show() {
    }

    /** Called when the screen should render itself.
     * @param delta The time in seconds since the last render.
     */
    override fun render(delta: Float) {
        // set background color
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()
        // tell the SpriteBatch to render in the coordinate system specified by the camera.
        game.batch.projectionMatrix = camera.combined

        game.batch.use {
            //display words "Main Menu"
            game.font.draw(it, "Main Menu", 100f, 150f)
        }

        if (Gdx.input.isTouched) {
            game.screen = ScreenOverworld(game)
            dispose()
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
    }

    /** Called when this screen is no longer the current screen for a [Game].  */
    override fun hide() {
    }

}