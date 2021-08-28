package entities

import com.soywiz.korge.box2d.body
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addUpdater
import views.PlayField

class Spring(playField: PlayField, image: Image) : BaseEntity(playField, image) {
    init {
        createBoxHitShape()

        addUpdater {
            if (touchingBeachBall) {
                playField.beachBall.body?.linearVelocityX = 0.0f
                playField.beachBall.body?.linearVelocityY = -100.0f
            }
        }
    }
}