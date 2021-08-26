package entities

import Game
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addUpdater
import views.PlayField

class Coin(game: Game, private val playField: PlayField, image: Image) : BaseEntity(playField, image) {
    init {
        playAnimationLooped()

        createCircleHitShape()

        addUpdater {
            if (touchingBeachBall) {
                game.coins++
                removeFromParent()
                game.resources.coinSound.play(game.views.coroutineContext)
            }
        }
    }
}