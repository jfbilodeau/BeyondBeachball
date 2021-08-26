package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Text
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.xy
import com.soywiz.korim.color.Colors
import scenes.PlayScene

class Hud(val playScene: PlayScene) : Container() {
    init {
        val coinCount = Text("", 64.0)
        val coinCountShadow = Text("", 64.0, Colors.BLACK).xy(2, 2)

        addChild(coinCountShadow)
        addChild(coinCount)

        addUpdater {
            val text = "Coins: ${playScene.game.coins} / ${playScene.game.maxCoins}"
            coinCount.text = text
            coinCountShadow.text = text
        }
    }
}