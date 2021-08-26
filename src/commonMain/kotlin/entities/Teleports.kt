package entities

import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addUpdater
import com.soywiz.korim.bitmap.Bitmaps
import views.PlayField

class Teleport(playField: PlayField, image: Image) : BaseEntity(playField, image) {
    init {
        bitmap = Bitmaps.transparent
        width = image.width
        height = image.height

        createBoxHitShape()

        addUpdater {
            if (touchingBeachBall) {
                val destinationName = "$name-destination"
                val destination = playField.getChildByName(destinationName)!!

                playField.beachBall.x = destination.x + playField.beachBall.width / 2
                playField.beachBall.y = destination.y + playField.beachBall.height / 2
            }
        }
    }
}

class TeleportDestination(playField: PlayField, image: Image) : BaseEntity(playField, image) {
    init {
        bitmap = Bitmaps.transparent
    }
}