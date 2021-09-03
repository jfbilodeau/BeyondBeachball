package scenes

import Game
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container

class LoadScene(val game: Game) : Scene() {
    override suspend fun Container.sceneInit() {
        game.resources.load()

        sceneContainer.changeTo<CodeScene>()
//        sceneContainer.changeTo<BeyondIntroScene>()
//        sceneContainer.changeTo<BeyondTitleScene>()
    }
}
