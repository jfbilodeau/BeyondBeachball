import com.soywiz.korge.scene.Module
import com.soywiz.korge.view.views
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt
import scenes.*

class GameModule : Module() {
    val game = Game()
    override val mainScene = LoadScene::class
    override val title = "Beachball"
    override val size = SizeInt(game.stageWidth, game.stageHeight)

    override suspend fun AsyncInjector.configure() {
        game.views = views()

        mapInstance( game )
        mapPrototype { LoadScene(get()) }
        mapPrototype { PlayScene(get()) }
        mapPrototype { IntroScene(get()) }
        mapPrototype { TitleScene(get()) }
        mapPrototype { EndScene(get()) }
        mapPrototype { CodeScene(get()) }
    }
}