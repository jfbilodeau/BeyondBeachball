package scenes

import Game
import com.soywiz.klock.seconds
import com.soywiz.kmem.bit
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.tween.get
import com.soywiz.korge.tween.tween
import com.soywiz.korge.ui.uiBreadCrumb
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.*
import com.soywiz.korim.format.readBitmapSlice
import com.soywiz.korio.async.launch
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.geom.unaryMinus
import com.soywiz.korma.interpolation.Easing

class BeyondEndScene(val game: Game) : Scene() {
    override suspend fun Container.sceneInit() {
        sprite {
            bitmap = resourcesVfs["compile.png"].readBitmapSlice()
        }

        val button = uiButton {
            text = "Yay! You fixed the code!"
            size(300, 100)
            xy(900, 600)
//            xy(1280 / 2, 768 / 2)

            onClick {
                sceneContainer.changeTo<TitleScene>()
            }
        }

        launch {
            while (true) {
                button.tween(button::rotation[(-16).degrees], time = 1.seconds, easing = Easing.EASE_IN_OUT)
                button.tween(button::rotation[16.degrees], time = 1.seconds, easing = Easing.EASE_IN_OUT)
            }
        }
    }
}
