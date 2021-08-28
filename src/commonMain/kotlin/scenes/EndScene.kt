package scenes

import Game
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.buttonBackColor
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.ktree.readKTree
import com.soywiz.korge.view.size
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.Colors
import com.soywiz.korio.file.std.resourcesVfs

class EndScene(val game: Game) : Scene() {
    override suspend fun Container.sceneInit() {
        resourcesVfs["end.ktree"].readKTree(this)

        uiButton {
            text = "REPLAY"
            xy(900, 680)
            size(300, 64)
            buttonBackColor = Colors.YELLOW

            onClick {
                enabled = false
                text = "LOADING..."
                game.reset()
                game.currentLevelIndex = 0
                sceneContainer.changeTo<PlayScene>()
            }
        }
    }
}
