package views

import com.soywiz.korge.box2d.Box2dWorldComponent
import com.soywiz.korge.box2d.getOrCreateBox2dWorld
import com.soywiz.korge.view.Container

class LevelView : Container() {
    private val world: Box2dWorldComponent = getOrCreateBox2dWorld()
    val background = Container()
    val middle = Container()
    val foreground = Container()


}