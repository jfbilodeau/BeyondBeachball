package scenes

import Game
import com.soywiz.klock.seconds
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.AlphaTransition
import com.soywiz.korge.scene.MaskTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.tween.get
import com.soywiz.korge.tween.tween
import com.soywiz.korge.ui.buttonBackColor
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.*
import com.soywiz.korge.view.filter.TransitionFilter
import com.soywiz.korge.view.tween.scaleTo
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.color.toColorAdd
import com.soywiz.korim.format.readBitmapSlice
import com.soywiz.korio.async.launch
import com.soywiz.korio.file.std.resourcesVfs

class BeyondIntroScene(val game: Game) : Scene() {
    override suspend fun Container.sceneInit() {
        sprite {
            bitmap = resourcesVfs["ide.png"].readBitmapSlice()
            scale = 2.0
        }

        uiButton {
            text = "START DEBUGGING"
            xy(1000, 600)
            size(250, 64)
//            buttonBackColor = Colors.YELLOW
            colorAdd = RGBA(255, 0, 0, 0).toColorAdd()

            onClick {
                enabled = false
                text = "LOADING..."
//                sceneContainer.changeTo<CodeScene>(transition = AlphaTransition, time = 0.5.seconds)
                launch(game.views.coroutineContext) {
                    text = ""
                    tween(this::width[10], this::height[10])
                    sceneContainer.changeTo<CodeScene>(transition = MaskTransition(TransitionFilter.Transition.DIAGONAL1), time = 0.5.seconds)
                }
            }
        }

        val filename = "File to debug: ${game.beyondLevels[game.currentBeyondLevelIndex].filename}"
//        val description = game.beyondLevels[game.currentBeyondLevelIndex].description

        text(filename) {
            textSize = 64.0
            xy(20.0, 20.0)
            color = Colors.BLACK
        }
        text(filename) {
            textSize = 64.0
            xy(20.0, 20.0)
            color = Colors.WHITE
        }

//        text(description) {
//            textSize = 32.0
//            xy(1202.0 - width, 652.0)
//            color = Colors.BLACK
//        }
//        text(description) {
//            textSize = 32.0
//            xy(1200.0 - width, 650.0)
//            color = Colors.WHITE
//        }
    }
}
