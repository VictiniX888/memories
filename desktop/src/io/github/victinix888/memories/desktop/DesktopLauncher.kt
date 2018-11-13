package io.github.victinix888.memories.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import io.github.victinix888.memories.Memories

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.title = "Memories"
        config.width = 800
        config.height = 480
        LwjglApplication(Memories(), config)
    }
}
