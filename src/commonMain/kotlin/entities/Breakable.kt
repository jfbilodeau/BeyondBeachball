package entities

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addUpdater
import com.soywiz.korio.dynamic.Dyn
import org.jbox2d.dynamics.BodyType
import views.PlayField
import kotlin.random.Random

class Debris(playField: PlayField, image: Image) : BaseEntity(playField, image) {
    init {
        createBoxBody(BodyType.DYNAMIC)

        alpha = 8.0

        addUpdater {
            alpha -= 0.01

            if (alpha <= 0) {
                removeFromParent()
            }
        }
    }
}

class Breakable(playField: PlayField, image: Image) : BaseEntity(playField, image) {
    init {
        createBoxHitShape()

        image.scale = 0.25

        addUpdater {
            if (touchingBeachBall) {
                for (i in 0..9) {
                    val debris = Debris(playField, image)
                    debris.x += Random.nextDouble(scaledWidth)
                    debris.y += Random.nextDouble(scaledHeight)

                    parent?.addChild(debris)
                }

                removeFromParent()
            }
        }
    }
}