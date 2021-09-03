package entities

import com.soywiz.korev.Key
import com.soywiz.korge.internal.KorgeInternal
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.collidesWithShape
import com.soywiz.korge.view.size
import com.soywiz.korim.color.RGBA
import com.soywiz.korim.color.toColorAdd
import com.soywiz.korma.geom.shape.Shape2d
import scenes.CodeScene
import scenes.TextToken

@OptIn(KorgeInternal::class)
class PlayerButton(val codeScene: CodeScene) : Container() {
    var weight = 12.0

    val button = uiButton() {
        text = " "
        size(10.0 + weight / 1000, 10.0 + weight / 1000)
        colorAdd = RGBA(255, 0, 0, 0).toColorAdd()
    }

    init {
        addUpdater {
            if (!hitShape2d.closed) {
                hitShape2d = Shape2d.Rectangle(0.0, 0.0, width, height)
            }
            val oldX = x
            val oldY = y

            val input = stage?.input

            if (input != null) {
                if (input.keys.pressing(Key.A)) {
                    x -= 5
                }
                if (input.keys.pressing(Key.D)) {
                    x += 5
                }
                if (input.keys.pressing(Key.W)) {
                    y -= 5
                }
                if (input.keys.pressing(Key.S)) {
                    y += 5
                }

                if (x < 0) {
                    x = 0.0
                }
                if (y < 22) {
                    y = 22.0
                }

                if (x + width >= 1280) {
                    x = 1280 - width
                }

                if (y + height >= 1280) {
                    y = 1280 - height
                }

                for (child in parent?.children!!) {
                    if (child is TextToken && collidesWithShape(child)) {
//                            println(child.weight)
                        if (child.weight < weight) {
                            weight += child.weight
//                            weight += 25
                            button.text = child.text
//                            child.removeFromParent()
                            codeScene.removeToken(child)

                            button.size(10.0 + weight / 1000, 10.0 + weight / 1000)
                            hitShape2d = Shape2d.Rectangle(0.0, 0.0, width, height)
                        } else {
                            x = oldX
                            y = oldY
                        }
                    }
                }
            }
        }
    }
}