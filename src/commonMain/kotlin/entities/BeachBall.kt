package entities

import Game
import com.soywiz.korge.box2d.BoxShape
import com.soywiz.korge.box2d.createBody
import com.soywiz.korge.box2d.fixture
import com.soywiz.korge.box2d.view
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.radians
import com.soywiz.korma.geom.vector.circle
import org.jbox2d.collision.shapes.CircleShape
import org.jbox2d.dynamics.BodyType
import scenes.PlayScene
import views.PlayField
import kotlin.random.Random

class BeachBall(playField: PlayField, game: Game) : Sprite(game.resources.beachball) {
    var contact = false

    init {
        name = "beachball"
        anchor(0.5, 0.5)
        hitShape {
            circle(0.0, 0.0, width / 2.0)
        }
        val body = playField.createBody {
            type = BodyType.DYNAMIC
        }.fixture {
            shape = CircleShape(game.resources.beachball.width / 2 / 20)
            density = 0.5f
            restitution = 0.5f
            friction = 0.1f
        }

        body.view = this

        addUpdater {
//            if (body.m_contactList != null) {
//                if (contact == false) {
//                    when(Random.nextInt(1, 3)) {
//                        1 -> game.resources.bounceSound1.play(game.views.coroutineContext)
//                        2 -> game.resources.bounceSound2.play(game.views.coroutineContext)
//                        3 -> game.resources.bounceSound3.play(game.views.coroutineContext)
//                    }
//                }
//
//                contact = true
//            } else {
//                contact = false
//            }
        }
    }
}