package scenes

import Game
import com.soywiz.klock.milliseconds
import com.soywiz.klock.seconds
import com.soywiz.korau.sound.readSound
import com.soywiz.korge.input.onClick
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.scene.delay
import com.soywiz.korge.ui.UITextButton
import com.soywiz.korge.ui.textSize
import com.soywiz.korge.view.*
import com.soywiz.korge.view.ktree.readKTree
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.readBitmapSlice
import com.soywiz.korio.async.launch
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.geom.plus
import kotlinx.coroutines.yield
import kotlin.random.Random

class BeyondTitleScene(val game: Game) : Scene() {
    var step = 0
    val button = UITextButton().apply {
        text = "PLAY"
        textSize = 64.0
        setSize(300.0, 64.0)
        xy(game.stageWidth / 2.0 - 150, game.stageHeight / 2.0 - 32)
        onClick {
            enabled = false
            step++
        }
    }

    val background = Sprite().apply {
        visible = false
    }

    override suspend fun Container.sceneInit() {
        resourcesVfs["beyond-title.ktree"].readKTree(this)
        background.bitmap = resourcesVfs["gpf.png"].readBitmapSlice()

        val error = resourcesVfs["error.mp3"].readSound()

        val error1 = text("Error: resourcesVfs[\"brain.py\"].readNeurons(): cognitive functions not found") {
            color = Colors.DARKRED
            textSize = 32.0
            xy(20, 10)
            rotation = 1.degrees
            visible = false
        }
        val error2 = text("java.long.NullPPPPPointerException") {
            color = Colors.DARKRED
            textSize = 32.0
            xy(20, 80)
            rotation = 355.degrees
            visible = false
        }
        addChild(background)
        addChild(button)

        //
        // java.long.NullPPPPointerException

        launch(game.views.coroutineContext) {
            waitForNextStep()

            loading()

            button.rotation += 1.degrees
            button.text = "Nope. Try again."
            button.enabled = true

            waitForNextStep()

            loading()

            button.rotation += 1.degrees
            button.text = "Oups! Something went wrong. Try again..."
            button.enabled = true

            waitForNextStep()

            loading()

            button.rotation += 1.degrees
            error1.visible = true
            button.text = "WTH??? Try again I guess..."
            button.enabled = true

            waitForNextStep()

            loading()

            error2.visible = true
            button.text = "Almost got it this time..."
            button.enabled = true

            waitForNextStep()

            loading()

            for (i in 1..100) {
                val clone = error2.clone().xy(
                    Random.nextDouble(10.0, 800.0),
                    Random.nextDouble(40.0, 600.0)
                )

                clone.rotation = Random.nextDouble(-5.0, 5.0).degrees

                addChild(clone)
                delay(10.milliseconds)
            }

            background.visible = true
            background.bringToTop()

            error.play(game.views.coroutineContext)
            delay(3.seconds)

            button.bringToTop()
            button.text = "Found the problem."
            button.enabled = true

            waitForNextStep()

            button.text = "Programmer is crap. Let's fix the code!"
            button.enabled = true

            waitForNextStep()

            sceneContainer.changeTo<BeyondIntroScene>()
        }
    }

    private suspend fun loading() {
        for (i in 1..3) {
            button.text = "LOADING."
            for (j in 1..4) {
                delay(500.milliseconds)
                button.text += "."
            }
        }
    }

    suspend fun waitForNextStep() {
        val currentStep = step

        while (currentStep == step) {
            yield()
        }
    }
}
