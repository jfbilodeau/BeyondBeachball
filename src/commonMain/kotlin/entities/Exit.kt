package entities

import com.soywiz.korge.animate.play
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addUpdater
import com.soywiz.korim.bitmap.Bitmaps
import org.jbox2d.dynamics.BodyType
import views.PlayField

class Exit(playField: PlayField, image: Image) : BaseEntity(playField, image) {
    init {
        createCircleBody(BodyType.STATIC)
        createCircleHitShape()

        addUpdater {
            if (playField.game.allCoinsCollected) {
                bitmap = Bitmaps.transparent
            }

            if (touchingBeachBall && playField.game.allCoinsCollected) {
                playField.game.nextLevel()
            }
        }
    }
}