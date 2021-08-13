package scenes

import Game
import com.soywiz.korge.box2d.*
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.SolidRect
import com.soywiz.korge.view.ktree.KTreeRoot
import com.soywiz.korge.view.ktree.readKTree
import com.soywiz.korio.file.std.resourcesVfs
import entities.Beachball
import org.jbox2d.dynamics.BodyType

class PlayScene(val game: Game) : Scene() {
    override suspend fun Container.sceneInit() {
        val world = getOrCreateBox2dWorld().world
//        world.customScale = 1.0
//        world.gravity.y *= 20

//        val base = SolidRect(1000.0, 10.0)
////        base.anchorX = 0.5
////        base.anchorY = 0.5
//
//        val body = createBody {
////            position.set(0.0f, 600.0f)
////            type = BodyType.DYNAMIC
//        }.fixture {
//            shape = BoxShape(1000.0 / 20, 10.0 / 20)
//        }
//
//        base.x = -10.0
//        base.y = 700.0
//
//        body.view = base
//
//        addChild(base)
        val scene = resourcesVfs["start.ktree"].readKTree(this) as KTreeRoot

        for (child in scene.children) {
            val body = createBody {
                type = BodyType.STATIC
            }.fixture {
                shape = BoxShape(child.width / 20, child.height / 20)
            }

            body.view = child
        }

        val ball = Beachball(this, game)
        ball.body?.angularVelocity = 100.0f
//        ball.x = 100.0
//        ball.y = 100.0

        scene.addChild(ball)
    }
}
