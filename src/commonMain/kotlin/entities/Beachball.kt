package entities

import Game
import com.soywiz.korge.box2d.BoxShape
import com.soywiz.korge.box2d.createBody
import com.soywiz.korge.box2d.fixture
import com.soywiz.korge.box2d.view
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.anchor
import org.jbox2d.collision.shapes.CircleShape
import org.jbox2d.dynamics.BodyType

class Beachball(container: Container, game: Game) : Sprite(game.resources.beachball) {
    init {
        anchor(0.5, 0.5)
        val body = container.createBody {
            type = BodyType.DYNAMIC
            bullet = true
//            angularVelocity = -2f
        }.fixture {
            shape = CircleShape(game.resources.beachball.width / 2 / 20)
            restitution = 1f
//            shape = BoxShape(game.resources.beachball.width, game.resources.beachball.height)
        }

        body.view = this

//        container.addChild(this)
    }
}