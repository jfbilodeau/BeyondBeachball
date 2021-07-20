package scenes

import com.soywiz.korge.box2d.createBody
import com.soywiz.korge.box2d.getOrCreateBox2dWorld
import com.soywiz.korge.box2d.view
import com.soywiz.korge.box2d.worldView
import com.soywiz.korge.scene.Scene
import com.soywiz.korge.view.Container
import com.soywiz.korge.view.Sprite

class EditorScene() : Scene() {
    override suspend fun Container.sceneInit() {
        // @TODO: Scene initialization here
		getOrCreateBox2dWorld()
    	worldView {
			createBody {

			}.view = Sprite()
		}
    }
}
