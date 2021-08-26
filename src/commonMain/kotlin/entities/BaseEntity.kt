package entities

import com.soywiz.korge.box2d.BoxShape
import com.soywiz.korge.box2d.createBody
import com.soywiz.korge.box2d.fixture
import com.soywiz.korge.box2d.view
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.shape.Shape2d
import org.jbox2d.collision.shapes.CircleShape
import org.jbox2d.dynamics.Body
import org.jbox2d.dynamics.BodyType
import views.PlayField

open class BaseEntity(private val playField: PlayField, image: Image) : Sprite(image.bitmap) {
    init {
        xy(image)
        name = image.name
    }

    fun createBoxBody(bodyType: BodyType): Body {
        val body = playField.createBody {
            type = bodyType
        }.fixture {
            shape = BoxShape(bitmap.width / 20, bitmap.height / 20)
            density = 1f
            restitution = 0.5f
        }

        body.view = this

        return body
    }

    fun createCircleBody(bodyType: BodyType): Body {
        val body = playField.createBody {
            type = bodyType
        }.fixture {
            shape = CircleShape(bitmap.width / 2 / 20)
            density = 0.5f
            restitution = 0.5f
            friction = 0.1f
        }

        body.view = this

        return body
    }

    fun createBoxHitShape() {
        hitShape2d = Shape2d.Rectangle(0.0, 0.0, width, height)
    }

    fun createCircleHitShape() {
        hitShape2d = Shape2d.Circle(scaledWidth / 2.0, scaledHeight / 2.0, scaledWidth / 2.0)
    }

    val touchingBeachBall: Boolean
        get() = collidesWith(playField.beachBall, CollisionKind.SHAPE)
}