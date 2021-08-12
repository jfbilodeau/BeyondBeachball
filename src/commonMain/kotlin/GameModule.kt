import com.soywiz.korge.scene.Module
import com.soywiz.korinject.AsyncInjector
import com.soywiz.korma.geom.SizeInt
import scenes.EditorScene
import scenes.LoadScene
import scenes.PlayScene

class GameModule : Module() {
    override val mainScene = LoadScene::class
    override val title = "Dimensional Miner"
    override val size = SizeInt(1280, 768)

    override suspend fun AsyncInjector.configure() {
        mapInstance( Game() )
        mapPrototype { LoadScene(get()) }
        mapPrototype { PlayScene(get()) }
        mapPrototype { EditorScene(get()) }
    }
}