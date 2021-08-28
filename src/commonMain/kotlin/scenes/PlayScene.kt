package scenes

import Game
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.scene.sceneContainer
import com.soywiz.korge.view.*
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

        addUpdater {
            if (game.touchedExit && !exited) {
                launch(game.views.coroutineContext) {
                    game.currentLevelIndex++
                    exited = true

                    if (game.currentLevelIndex < game.levels.size) {
                        sceneContainer.changeTo<LevelIntro>()
                    } else {
                        sceneContainer.changeTo<EndScene>()
                    }
                }
            }
        }
    }
}

