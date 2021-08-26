package entities

import Game
import com.soywiz.kmem.bit
import com.soywiz.korau.sound.Sound
import com.soywiz.korge.box2d.*
import com.soywiz.korge.view.*
import org.jbox2d.dynamics.BodyType
import views.PlayField

class Tunnel(container: Container, oldImage: Image) : Sprite(oldImage.bitmap) {
    init {
        xy(oldImage)
        name = oldImage.name

        val body = container.createBody {
            type = BodyType.STATIC
        }.fixture {
            isSensor = true
            shape = BoxShape(bitmap.width / 20, bitmap.height / 20)
        }

        body.view = this

        onCollision {
            if (it is Vehicle) {
                val entranceName = "$name-entrance"
                val entrance = container.getChildByName(entranceName)!!

                it.x = entrance.x
            }
        }
    }
}

class Vehicle(playField: PlayField, image: Image, val sound: Sound) : BaseEntity(playField, image) {
    init {
        val body = createBoxBody(BodyType.KINEMATIC)
        createBoxHitShape()

        addUpdater {
            body.linearVelocityX = -15f

            if (touchingBeachBall) {
                sound.play(playField.game.views.coroutineContext)
            }
        }
    }
}