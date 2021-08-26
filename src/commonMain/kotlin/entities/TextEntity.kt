package entities

import com.soywiz.korge.view.*
import com.soywiz.korma.geom.shape.Shape2d
import views.PlayField

class TextEntity(val playField: PlayField, child: Text) : Text(child.text, child.textSize) {
    init {
        xy(child)
        createBoxHitShape()

        alpha = 0.0

        addUpdater {
            if (touchingBeachBall) {
                alpha = 3.0
            } else if (alpha > 0.0) {
                alpha -= 0.01
            } else {
                alpha = 0.0
            }
        }
    }

    private fun createBoxHitShape() {
        hitShape2d = Shape2d.Rectangle(0.0, 0.0, width, height)
    }

    val touchingBeachBall: Boolean
        get() = collidesWith(playField.beachBall, CollisionKind.SHAPE)

}