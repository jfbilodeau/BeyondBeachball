package entities

import com.soywiz.korge.animate.play
import com.soywiz.korge.scene.SceneContainer
import com.soywiz.korge.scene.sceneContainer
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.anchor
import com.soywiz.korim.bitmap.Bitmaps
import com.soywiz.korio.async.launch
import org.jbox2d.dynamics.BodyType
import scenes.LevelIntro
import views.PlayField

class BlockedExit(playField: PlayField, image: Image) : BaseEntity(playField, image) {
    var exited = false

    init {
        val body = createCircleBody(BodyType.STATIC)
        createCircleHitShape()

        addUpdater {
            if (playField.game.allCoinsCollected) {
                alpha = 0.0

                if (touchingBeachBall) {
                    playField.game.touchedExit = true
                }
            }
        }
    }
}

class RealExit(playField: PlayField, image: Image) : BaseEntity(playField, image) {
    init {
        anchor(0.5, 0.5)

    }
}