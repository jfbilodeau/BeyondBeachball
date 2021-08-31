package entities

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.anchor
import org.jbox2d.dynamics.BodyType
import views.PlayField

class BlockedExit(playField: PlayField, image: Image) : BaseEntity(playField, image) {
    init {
        val body = createCircleBody(BodyType.STATIC)
        createCircleHitShape()

        addUpdater {
            if (playField.game.allCoinsCollected) {
                alpha = 0.0

                if (touchingBeachBall) {
                    playField.beachBall.removeFromParent()
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