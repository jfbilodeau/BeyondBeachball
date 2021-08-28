package entities

import com.soywiz.klock.seconds
import com.soywiz.korge.animate.animateParallel
import com.soywiz.korge.tween.get
import com.soywiz.korge.view.Image
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.filter.WaveFilter
import com.soywiz.korio.async.launch
import com.soywiz.korma.interpolation.Easing
import org.jbox2d.dynamics.BodyType
import views.PlayField

class Water(playField: PlayField, image: Image) : BaseEntity(playField, image) {
    init {
        createBoxBody(BodyType.STATIC)

        val wave = WaveFilter(40, 40)

        filter = wave

        launch(playField.game.views.coroutineContext) {
            animateParallel {
                sequence(looped = true) {
                    tween(wave::time[1.seconds], time = 1.seconds, easing = Easing.LINEAR)
                    tween(wave::time[0.seconds], time = 1.seconds, easing = Easing.LINEAR)
                }

            }
        }
    }
}