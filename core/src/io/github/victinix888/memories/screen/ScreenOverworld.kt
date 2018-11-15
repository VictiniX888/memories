package io.github.victinix888.memories.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.maps.Map
import com.badlogic.gdx.maps.MapRenderer
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TiledMapRenderer
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import io.github.victinix888.memories.Memories
import io.github.victinix888.memories.map.loadMap
import ktx.graphics.use

const val WORLD_WIDTH: Float = 32f  //size of world in tiles
const val WORLD_HEIGHT: Float = 32f

class ScreenOverworld(val game: Memories) : Screen {

    //private var mapSprite: Sprite
    private var map: TiledMap
    private var mapRenderer: TiledMapRenderer
    private var playerSprite: Texture
    private var player: Rectangle
    private var camera: OrthographicCamera

    init {
        //mapSprite = Sprite(Texture(Gdx.files.internal("map.png")))
        //mapSprite.setPosition(0f, 0f)
        //mapSprite.setSize(WORLD_WIDTH, WORLD_HEIGHT)

        map = loadMap(Texture(Gdx.files.internal("map.png")))
        mapRenderer = OrthogonalTiledMapRenderer(map, 1/16f)

        playerSprite = Texture(Gdx.files.internal("player.png"))
        //position player at bottom-left corner (this gets overriden in render()
        player = Rectangle(0f, 0f, 1f, 1f)

        val screenWidth: Float = Gdx.graphics.width.toFloat()   //in pixels
        val screenHeight: Float = Gdx.graphics.height.toFloat() //in pixels
        //creates the camera with width of 9 units (not pixels) and height according to aspect ratio
        camera = OrthographicCamera(9f, 9f * (screenHeight/screenWidth))
        //position camera at world center
        //position of camera is set according to units and not pixels
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0f)
        camera.update()
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
        handleInput(delta)

        camera.update()
        // tell the SpriteBatch to render in the coordinate system specified by the camera.
        game.batch.projectionMatrix = camera.combined

        //update player position, to position player at centre of camera
        player.x = camera.position.x - (player.width / 2f)
        player.y = camera.position.y - (player.height / 2f)

        //clear screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        mapRenderer.setView(camera)
        mapRenderer.render()

        game.batch.use {
            //mapSprite.draw(it)
            it.draw(playerSprite, player.x, player.y, player.width, player.height, 0, 0, 16, 16, false, false)
        }
    }

    private fun handleInput(delta: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-4f*delta, 0f)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(4f*delta, 0f)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0f, -4f*delta)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0f, 4f*delta)
        }

        //prevents camera moving out of bounds
        val viewportWidthCentre: Float = camera.viewportWidth / 2f
        val viewportHeightCentre: Float = camera.viewportHeight / 2f
        camera.position.x = MathUtils.clamp(camera.position.x, viewportWidthCentre, WORLD_WIDTH - viewportWidthCentre)
        camera.position.y = MathUtils.clamp(camera.position.y, viewportHeightCentre, WORLD_HEIGHT - viewportHeightCentre)
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
        camera.viewportWidth = 9f
        camera.viewportHeight = 9f * (height.toFloat()/width.toFloat())
        camera.update()
    }

    /** Called when this screen should release all resources.  */
    override fun dispose() {
        playerSprite.dispose()
        map.dispose()
    }
}