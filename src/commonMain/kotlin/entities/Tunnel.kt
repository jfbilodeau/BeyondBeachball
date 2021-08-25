package entities

import Game
import com.soywiz.kmem.bit
import com.soywiz.korge.box2d.BoxShape
import com.soywiz.korge.box2d.createBody
import com.soywiz.korge.box2d.fixture
import com.soywiz.korge.box2d.view
import com.soywiz.korge.view.*
import org.jbox2d.dynamics.BodyType

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

class Vehicle(container: Container, oldImage: Image) : Sprite(oldImage.bitmap) {
    init {
        xy(oldImage)

        val body = container.createBody {
            type = BodyType.KINEMATIC
        }.fixture {
            shape = BoxShape(bitmap.width / 20, bitmap.height / 20)
            density = 1f
            restitution = 0.5f
        }

        body.view = this

        addUpdater {
            body.linearVelocityX = -15f
        }
    }
}