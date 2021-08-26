package entities

import com.soywiz.korge.box2d.body
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addUpdater
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.toColorAdd
import com.soywiz.korma.geom.cosine
import com.soywiz.korma.geom.sine
import views.PlayField
import kotlin.random.Random

class Bumper(playField: PlayField, image: Image) : BaseEntity(playField, image) {
    init {
        createCircleHitShape()

        addUpdater {
            colorAdd = Colors.TRANSPARENT_BLACK.toColorAdd()

            if (touchingBeachBall) {
                val angle = pos.angleTo(playField.beachBall.pos)
                val velX = angle.cosine * 75.0
                val velY = angle.sine * 75.0

                playField.beachBall.body?.linearVelocityX = velX.toFloat()
                playField.beachBall.body?.linearVelocityY = velY.toFloat()

                colorAdd = Colors.YELLOW.toColorAdd()

                val sound = when (Random.nextInt(1, 3)) {
                    1 -> playField.game.resources.bounceSound1
                    2 -> playField.game.resources.bounceSound2
                    3 -> playField.game.resources.bounceSound3
                    else -> null
                }

                sound?.play(playField.game.views.coroutineContext)
            }
        }
    }
}