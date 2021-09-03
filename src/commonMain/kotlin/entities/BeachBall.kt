package entities

import Game
import com.soywiz.korge.box2d.body
import com.soywiz.korge.box2d.createBody
import com.soywiz.korge.box2d.fixture
import com.soywiz.korge.box2d.view
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.anchor
import com.soywiz.korge.view.hitShape
import com.soywiz.korma.geom.vector.circle
import org.jbox2d.collision.shapes.CircleShape
import org.jbox2d.dynamics.BodyType
import views.PlayField

class BeachBall(playField: PlayField, game: Game) : Sprite(game.resources.beachball) {
    var contact = false

    init {
        name = "beachball"
        anchor(0.5, 0.5)
        hitShape {
            circle(width / 2.0, height / 2.0, width / 2.0)
        }
        val body = playField.createBody {
            type = BodyType.DYNAMIC
        }.fixture {
            shape = CircleShape(width / 2 / 20)
            density = 1.0f
            restitution = 0.5f
            friction = 0.1f
        }

        body.view = this
        this.body = body
    }
}