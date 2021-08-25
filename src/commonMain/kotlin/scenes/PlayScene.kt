package scenes

import Game
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.*
import com.soywiz.korge.view.ktree.KTreeRoot
import com.soywiz.korge.view.ktree.readKTree
import com.soywiz.korim.bitmap.slice
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import views.PlayField

class PlayScene(val game: Game) : Scene() {
    val playField = PlayField(game)
    val background = Sprite()

    override suspend fun Container.sceneInit() {
        background.bitmap = resourcesVfs["sewers-background.png"].readBitmap().slice()
        val scene = resourcesVfs["sewers.ktree"].readKTree(playField) as KTreeRoot

        playField.setLevel(scene)

        addChild(background)
        addChild(playField)
    }
}
