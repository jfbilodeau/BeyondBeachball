package entities

import com.soywiz.korge.animate.play
import com.soywiz.korge.scene.SceneContainer
import com.soywiz.korge.scene.sceneContainer
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addUpdater
import com.soywiz.korim.bitmap.Bitmaps
import com.soywiz.korio.async.launch
import org.jbox2d.dynamics.BodyType
import scenes.LevelIntro
import views.PlayField

class Exit(playField: PlayField, image: Image) : BaseEntity(playField, image) {
    var exited = false
    init {
        val body = createCircleBody(BodyType.STATIC)
        createCircleHitShape()

        addUpdater {
            if (playField.game.allCoinsCollected) {
                bitmap = Bitmaps.transparent
            }

            if (touchingBeachBall && playField.game.allCoinsCollected) {
                if (!exited) {
                    launch(playField.game.views.coroutineContext) {
                        exited = true
                        playField.game.currentLevelIndex++

                        playField.sceneContainer {
                            changeTo<LevelIntro>()
                        }
                    }
                }
            }
        }
    }
}