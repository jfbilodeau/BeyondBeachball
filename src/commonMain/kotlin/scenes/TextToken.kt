package scenes

import com.soywiz.korge.view.Text
import com.soywiz.korim.font.Font
import com.soywiz.korim.font.getTextBounds
import com.soywiz.korma.geom.shape.Shape2d

class TextToken(text: String, font: Font) : Text(text, 32.0, font = font) {
    var weight = 0.0

    init {
        val metrics = font.getTextBounds(32.0, text)

        val baseLine = 0.0

        val left = metrics.left
        val top = metrics.fontMetrics.ascent - metrics.drawTop //metrics.height - metrics.drawTop
        val width = metrics.width
        val height = metrics.height // metrics.ascent - metrics.descent

        weight = metrics.width * metrics.height

        hitShape2d = Shape2d.Rectangle(
            left,
            top,
            width,
            height
        )

//        val rect = RoundRect(width, height, 3.0, 3.0, Colors.TRANSPARENT_WHITE, Colors.CYAN, 1.0).xy(x + left, y + top)
//        addChild(rect)
    }
}