package entities

import com.soywiz.korge.animate.play
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.Sprite
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.xy
import com.soywiz.korim.bitmap.Bitmaps
import scenes.PlayScene
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
                println(destinationName)
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