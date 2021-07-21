import com.soywiz.korge.view.Sprite
import org.jbox2d.common.Vec2
import org.jbox2d.dynamics.Fixture

abstract class Entity(val type: EntityType) : Sprite() {
    var fixture: Fixture? = null

    fun pointInside(x: Double, y: Double): Boolean {
        return fixture?.testPoint(Vec2(x.toFloat(), y.toFloat())) ?: pointInside(x, y)
    }

    open fun update(game: Game) {}
}