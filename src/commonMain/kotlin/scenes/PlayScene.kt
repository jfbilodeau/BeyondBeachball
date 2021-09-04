package scenes

import Game
import com.soywiz.klock.seconds
import com.soywiz.korau.sound.readMusic
import com.soywiz.korau.sound.readSound
import com.soywiz.korge.scene.AlphaTransition
import com.soywiz.korge.scene.MaskTransition
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korge.view.filter.TransitionFilter
import com.soywiz.korge.view.ktree.KTreeRoot
import com.soywiz.korge.view.ktree.readKTree
import com.soywiz.korim.bitmap.slice
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.async.launch
import com.soywiz.korio.file.std.resourcesVfs
import views.Hud
import views.PlayField

class PlayScene(val game: Game) : Scene() {
    val playField = PlayField(game)
    val background = Sprite()
    var exited = false

    override suspend fun Container.sceneInit() {
        background.bitmap = resourcesVfs[game.currentLevel.background].readBitmap().slice()
        val scene = resourcesVfs[game.currentLevel.ktree].readKTree(playField) as KTreeRoot

        playField.setLevel(scene)

        addChild(background)
        addChild(playField)
        addChild(Hud(this@PlayScene))

        val music = resourcesVfs["Cephelopod.mp3"].readSound()
        val channel = music.playForever(game.views.coroutineContext)

        addUpdater {
            if (game.touchedExit && !exited) {
                game.currentLevelIndex++
                exited = true
                channel.stop()

                launch(game.views.coroutineContext) {

                    if (game.currentLevelIndex < game.levels.size) {
                        sceneContainer.changeTo<IntroScene>(transition = MaskTransition(TransitionFilter.Transition.DIAGONAL1), time = 0.5.seconds)
                    } else {
                        sceneContainer.changeTo<EndScene>(transition = MaskTransition(TransitionFilter.Transition.HORIZONTAL), time = 2.seconds)
                    }
                }
            }
        }
    }
}

