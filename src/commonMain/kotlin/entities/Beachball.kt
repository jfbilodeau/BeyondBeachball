package entities

import Game
import com.soywiz.korge.box2d.createBody
import com.soywiz.korge.box2d.fixture
import com.soywiz.korge.box2d.view
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import org.jbox2d.collision.shapes.CircleShape
import org.jbox2d.dynamics.BodyType

class Beachball(container: Container, game: Game): Sprite(game.resources.beachball) {
    init {
        val body = container.createBody {
            type = BodyType.DYNAMIC
//            angularVelocity = -2f
        }.fixture {
            shape = CircleShape(game.resources.beachball.width/2)
        }

        body.view = this

//        container.addChild(this)
    }
}