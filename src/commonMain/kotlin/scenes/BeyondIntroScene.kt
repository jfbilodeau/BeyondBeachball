package scenes

import Game
import com.soywiz.klock.seconds
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.AlphaTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.ui.buttonBackColor
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmapSlice
import com.soywiz.korio.file.std.resourcesVfs

class BeyondIntroScene(val game: Game) : Scene() {
    override suspend fun Container.sceneInit() {
        sprite {
            bitmap = resourcesVfs["ide.png"].readBitmapSlice()
            scale = 2.0
        }

        uiButton {
            text = "START"
            xy(900, 680)
            size(300, 64)
            buttonBackColor = Colors.YELLOW

            onClick {
                enabled = false
                text = "LOADING..."
//                sceneContainer.changeTo<CodeScene>(transition = AlphaTransition, time = 0.5.seconds)
                sceneContainer.changeTo<CodeScene>()
            }
        }

        val description = game.beyondLevels[game.currentBeyondLevelIndex].description

        text(description) {
            textSize = 32.0
            xy(1202.0 - width, 652.0)
            color = Colors.BLACK
        }
        text(description) {
            textSize = 32.0
            xy(1200.0 - width, 650.0)
            color = Colors.WHITE
        }
    }
}
