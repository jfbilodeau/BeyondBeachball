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
        scaleX = image.scaleX
        scaleY = image.scaleY
        xy(image)
        name = image.name
    }

    fun createBoxBody(bodyType: BodyType): Body {
        val body = playField.createBody {
            type = bodyType
        }.fixture {
            shape = BoxShape(scaledWidth / 20, scaledHeight / 20)
            density = 1.0f
            friction = 0.5f
            restitution = 0.5f
        }

        body.view = this

        return body
    }

    fun createCircleBody(bodyType: BodyType): Body {
        anchor(0.5, 0.5)
        x += width / 2.0
        y += height / 2.0

        val body = playField.createBody {
            type = bodyType
        }.fixture {
            shape = CircleShape(scaledHeight / 2.2 / 20.0)
            density = 1.0f
            friction = 0.5f
            restitution = 0.5f
        }

        body.view = this

        return body
    }

    fun createBoxHitShape() {
        hitShape2d = Shape2d.Rectangle(0.0, 0.0, scaledWidth, scaledHeight)
    }

    fun createCircleHitShape() {
        hitShape2d = Shape2d.Circle(scaledWidth / 2.0 - anchorX, scaledHeight / 2.0 - anchorY, scaledWidth / 2.0)
    }

    val touchingBeachBall: Boolean
        get() = collidesWith(playField.beachBall, CollisionKind.SHAPE)
}