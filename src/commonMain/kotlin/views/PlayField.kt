package views

import Game
import com.soywiz.korge.box2d.*
import com.soywiz.korge.view.*
import com.soywiz.korge.view.ktree.KTreeRoot
import entities.*
import org.jbox2d.dynamics.BodyType
import scenes.PlayScene

class PlayField(val game: Game) : Container() {
    val beachBall = BeachBall(this, game)

    fun setLevel(level: KTreeRoot) {
        val removeMe = mutableListOf<View>()

        for (child in level.children) {
            when {
                child is Image -> {
                    when (child.sourceFile) {
                        "left-flipper-200.png" -> {
                            removeMe.add(child)
                            val leftFlipper = LeftFlipper(level, game)
                            leftFlipper.xy(child.x, child.y)
                            addChild(leftFlipper)
                        }
                        "right-flipper-200.png" -> {
                            removeMe.add(child)
                            val rightFlipper = RightFlipper(level, game)
                            rightFlipper.xy(child.x + rightFlipper.width, child.y)
                            addChild(rightFlipper)
                        }
                        "mouse-200.png" -> {
                            removeMe.add(child)
                            val mouse = Vehicle(level, child)
                            addChild(mouse)
                        }
                        "mouse-hole-exit-200.png" -> {
                            removeMe.add(child)
                            val tunnel = Tunnel(level, child)
                            addChild(tunnel)
                        }
                        "coin-placeholder.png" -> {
                            removeMe.add(child)
                            val coin = Coin(game, this, child)
                            addChild(coin)
                            game.maxCoins++
                        }
                        "teleport.png" -> {
                            removeMe.add(child)
                            val teleport = Teleport(this, child)
                            addChild(teleport)
                        }
                        "teleport-destination.png" -> {
                            removeMe.add(child)
                            val teleportDestination = TeleportDestination(this, child)
                            addChild(teleportDestination)
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
            level.removeChild(it)
        }

//        val ball = Beachball(this, game)
        beachBall.xy(200, 0)
//        ball.body?.angularVelocity = MathUtils.PI
//        ball.x = 100.0
//        ball.y = 100.0

        addChild(beachBall)

        addUpdater {
            // TODO: Scroll when ball is closer to the edge
            x = -beachBall.x + game.stageWidth / 2
            y = -beachBall.y + game.stageHeight / 2
//            xy(-780, 1900)
        }
    }
}
