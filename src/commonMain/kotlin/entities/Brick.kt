package entities

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addUpdater
import org.jbox2d.dynamics.BodyType
import views.PlayField

class Brick(playField: PlayField, image: Image) : BaseEntity(playField, image) {
    init {
        createBoxBody(BodyType.DYNAMIC)
        createBoxHitShape()
    }
}