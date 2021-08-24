package scenes

import Game
import com.soywiz.korge.box2d.*
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korge.view.ktree.KTreeRoot
import com.soywiz.korge.view.ktree.readKTree
import com.soywiz.korio.file.std.resourcesVfs
import entities.*
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

        val removeMe = mutableListOf<View>()

        for (child in scene.children) {
            when {
                child is Image -> {
                    when (child.sourceFile) {
                        "left-flipper-200.png" -> {
                            removeMe.add(child)
                            val leftFlipper = LeftFlipper(scene, game)
                            leftFlipper.xy(child.x, child.y)
                            addChild(leftFlipper)
                        }
                        "right-flipper-200.png" -> {
                            removeMe.add(child)
                            val rightFlipper = RightFlipper(scene, game)
                            rightFlipper.xy(child.x + rightFlipper.width, child.y)
                            addChild(rightFlipper)
                        }
                        "mouse-200.png" -> {
                            removeMe.add(child)
                            val mouse = Vehicle(scene, child)
                            addChild(mouse)
                        }

                        "mouse-hole-exit-200.png" -> {
                            removeMe.add(child)
                            val tunnel = Tunnel(scene, child)
                            addChild(tunnel)
                        }
                    }
                }
            }

            if (removeMe.contains(child) == false) {
                val body = createBody {
                    type = BodyType.STATIC
                }.fixture {
                    shape = BoxShape(child.scaledWidth / 20, child.scaledHeight / 20)
                }

                body.view = child
            }
        }

        removeMe.forEach {
            scene.removeChild(it)
        }

        val ball = Beachball(this, game)
        ball.xy(200, 0)
//        ball.body?.angularVelocity = MathUtils.PI
//        ball.x = 100.0
//        ball.y = 100.0

        addChild(ball)

        addUpdater {
            // TODO: Scroll when ball is closer to the edge
//            x = -ball.x + stage!!.width / 2
//            y = -ball.y + stage!!.height / 2
            xy(-780, 1900)
        }
    }
}
