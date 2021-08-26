package entities

import Game
import com.soywiz.korge.view.*
import com.soywiz.korma.geom.vector.circle
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