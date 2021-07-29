import com.soywiz.korge.view.Sprite

open class Entity : Sprite() {
    var id = 0
    var type = ""

    open fun update() { /* Override as necessary */ }
}