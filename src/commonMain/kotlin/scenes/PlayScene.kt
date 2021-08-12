package scenes

import Game
import com.soywiz.korge.box2d.*
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.SolidRect
import entities.Beachball

class PlayScene(val game: Game) : Scene() {
    override suspend fun Container.sceneInit() {
        getOrCreateBox2dWorld()

        val base = SolidRect(1000.0, 10.0)
        base.y = 700.0

        val body = createBody {
//            position.set(0.0f, 600.0f)
//            type = BodyType.DYNAMIC
        }.fixture {
            shape = BoxShape(1000.0, 10.0)
        }

        body.view = base

        addChild(base)

        val ball = Beachball(this, game)

//        addChild(ball)
    }
}
