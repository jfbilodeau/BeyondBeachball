package scenes

import Game
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.textFont
import com.soywiz.korge.ui.textSize
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.ktree.readKTree
import com.soywiz.korge.view.xy
import com.soywiz.korio.file.std.resourcesVfs

class TitleScene(val game: Game) : Scene() {
    override suspend fun Container.sceneInit() {
        resourcesVfs["title.ktree"].readKTree(this)

        uiButton {
            text = "PLAY"
            textSize = 64.0
            setSize(300.0, 64.0)
            xy(game.stageWidth / 2.0 - 150, game.stageHeight / 2.0 - 32)
            onClick {
                sceneContainer.changeTo<LevelIntro>()
            }
        }
    }
}
