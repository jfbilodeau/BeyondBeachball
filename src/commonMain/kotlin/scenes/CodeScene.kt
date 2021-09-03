package scenes

import Game
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korim.font.readTtfFont
import com.soywiz.korim.format.readBitmap
import com.soywiz.korim.format.readBitmapSlice
import com.soywiz.korio.file.std.resourcesVfs
import entities.PlayerButton
import views.BeyondHud

class CodeScene(val game: Game) : Scene() {
    val button = PlayerButton(this)
    val hud = BeyondHud(this)
    val garbage = Sprite()
    var tokenCount = 0

    override suspend fun Container.sceneInit() {
        sprite(resourcesVfs["screen.png"].readBitmap()) {}

        val sourceCode = resourcesVfs[game.beyondLevels[game.currentBeyondLevelIndex].filename].readString()

        val font = resourcesVfs["font/Courier Regular.ttf"].readTtfFont()

        val space = Text("M", 32.0, font = font)
        space.autoSize

        val marginLeft = 32.0
        val marginTop = 32.0

        var x = marginLeft
        var y = marginTop

        var i = 0

        fun emitText(text: String) {
            val textView = TextToken(text, font).xy(x, y)
            addChild(textView)
            tokenCount++
        }

        while (i < sourceCode.length) {
            val c = sourceCode[i]
            when {
                c == ' ' -> x += space.width
                c == '\n' -> {
                    x = marginLeft
                    y += space.height
                }
                c.isDigit() -> {
                    var buffer = ""
                    do {
                        buffer += sourceCode[i]
                        i++
                    } while (i < sourceCode.length && sourceCode[i].isDigit())
                    i--

                    emitText(buffer)
                    x += space.width * buffer.length
                }
                c.isLetter() -> {
                    var buffer = ""
                    do {
                        buffer += sourceCode[i]
                        i++
                    } while (i < sourceCode.length && sourceCode[i].isLetter())
                    i--

                    emitText(buffer)
                    x += space.width * buffer.length
                }
                else -> {
                    emitText(c.toString())
                    x += space.width
                }
            }

            i++
        }

        button.xy(game.stageWidth / 2 - button.width / 2, game.stageHeight / 2 - button.width / 2)

        garbage.bitmap = resourcesVfs["garbage-100.png"].readBitmapSlice()
        garbage.xy(1180, 0)

        addChild(button)
        addChild(garbage)
        addChild(hud)
    }
}
