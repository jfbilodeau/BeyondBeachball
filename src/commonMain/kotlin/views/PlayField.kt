package views

import Game
import com.soywiz.korev.Key
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
            var createBody = false

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
                        "fish.png",
                        "mouse-200.png",
                        "car-1-320.png",
                        "car-2-320.png",
                        "car-3-320.png" -> {
                            val sound = when (child.sourceFile) {
                                "fish.png" -> game.resources.fishSound
                                "mouse-200.png" -> game.resources.mouseSound
                                else -> game.resources.hornSound
                            }

                            removeMe.add(child)
                            val mouse = Vehicle(this, child, sound)
                            addChild(mouse)
                        }
                        "car-exit.png",
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
                        "mushroom-200.png",
                        "mushroom-house-300.png",
                        "seashell-bumper-2.png",
                        "seashell-bumper.png" -> {
                            removeMe.add(child)
                            val bumper = Bumper(this, child)
                            addChild(bumper)
                        }
                        "shark.png",
                        "no-entry-300.png",
                        "manhole-300.png" -> {
                            removeMe.add(child)
                            val exit = BlockedExit(this, child)
                            addChild(exit)
                        }
                        "shell-1.png",
                        "shell-2.png",
                        "shell-3.png",
                        "old-house.png" -> {
                            removeMe.add(child)
                            val house = Breakable(this, child)
                            addChild(house)
                        }
                        "brick-80.png" -> {
                            removeMe.add(child)
                            val brick = Brick(this, child)
                            addChild(brick)
                        }
                        "spring.png" -> {
                            removeMe.add(child)
                            val spring = Spring(this, child)
                            addChild(spring)
                        }
                        "water.png" -> {
                            removeMe.add(child)
                            val water = Water(this, child)
                            addChild(water)
                        }
                        else -> {
                            createBody = child.sourceFile?.startsWith("bg_") == false
                        }
                    }
                }
                child is Text -> {
                    removeMe.add(child)
                    val textEntity = TextEntity(this, child)
                    addChild(textEntity)
                }
            }

            if (createBody) {
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

        beachBall.xy(game.currentLevel.start.x, game.currentLevel.start.y)

        addChild(beachBall)

        addUpdater {
            // TODO: Scroll when ball is closer to the edge
            x = -beachBall.x + game.stageWidth / 2
            y = -beachBall.y + game.stageHeight / 2
//            xy(-780, 1900)

            if (game.views.input.keys.pressing(Key.R)) {
                beachBall.pos = game.currentLevel.start
            }
            if (game.views.input.keys.pressing(Key.C)) {
                forEachChild {
                    if (it is Coin) {
                        beachBall.pos = it.pos
                    }
                }
            }
        }
    }
}
