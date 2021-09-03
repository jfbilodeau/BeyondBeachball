package entities

import com.soywiz.korev.Key
import com.soywiz.korge.internal.KorgeInternal
import com.soywiz.korge.ui.uiButton
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.collidesWithShape
import com.soywiz.korge.view.size
import com.soywiz.korma.geom.shape.Shape2d
import scenes.TextToken

@OptIn(KorgeInternal::class)
class PlayerButton : Container() {
    var weight = 400.0

    val button = uiButton() {
        text = (weight / 100).toInt().toString()
        size(10.0, 10.0)
    }

    init {
        hitShape2d = Shape2d.Rectangle(0.0, 0.0, width, height)

        addUpdater {
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
                if (y < 0) {
                    y = 0.0
                }

                for (child in parent?.children!!) {
                    if (child != this && collidesWithShape(child)) {
                        if (child is TextToken) {
                            if (child.weight < weight) {
                                weight += child.weight
                                child.removeFromParent()
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
}