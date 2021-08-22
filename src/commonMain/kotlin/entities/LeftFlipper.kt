package entities

import Game
import com.soywiz.klock.timesPerSecond
import com.soywiz.korev.Key
import com.soywiz.korge.box2d.BoxShape
import com.soywiz.korge.box2d.createBody
import com.soywiz.korge.box2d.fixture
import com.soywiz.korge.box2d.view
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.addFixedUpdater
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.geom.unaryMinus
import org.jbox2d.dynamics.BodyType

class LeftFlipper(container: Container, game: Game) : Sprite(game.resources.leftFlipper) {
    init {
//        anchor(20, bitmap.height / 2)
        anchorY = 0.5
        rotation = 30.0.degrees
        val body = container.createBody {
            type = BodyType.KINEMATIC
        }.fixture {
            shape = BoxShape(bitmap.width / 20, bitmap.height / 20)
            density = 1f
            restitution = 1f
        }

        body.view = this

        addFixedUpdater(60.timesPerSecond) {
            val input = stage?.input

            if (input?.keys?.pressing(Key.LEFT_SHIFT) == true) {
                if (rotation > -30.0.degrees) {
//                rotation = -30.0.degrees
                    body.angularVelocity = -10.0f
                } else {
                    rotation = -30.0.degrees
                }
//                body.bodyDef.angleRadians = 1.0f
//                body.linearVelocityX = 1.0f
//                body.setTransform(body.position, rotation - 0.2.radians)
//                body.isActive = true
            } else {
                if (rotation < 30.0.degrees) {
//                rotation = -30.0.degrees
                    body.angularVelocity = 10.0f
                } else {
                    rotation = 30.0.degrees
                }
//                rotation = 30.0.degrees
            }
        }
    }
}