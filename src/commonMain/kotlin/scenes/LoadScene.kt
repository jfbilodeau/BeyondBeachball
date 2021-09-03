package scenes

import Game
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class LoadScene(val game: Game) : Scene() {
    override suspend fun Container.sceneInit() {
        game.resources.load()

        sceneContainer.changeTo<CodeScene>()
    }
}
