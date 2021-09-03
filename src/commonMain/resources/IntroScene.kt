package scenes

class IntroScene(val game: Game) : Scene() {
    override suspend fun Container.sceneInit() {
        resourcesVfs[game.currentLevel.intro].readKTree(this)

        uiButton {
            text = "START"
            xy(900, 680)
            size(300, 64)
            buttonBackColor = Colors.YELLOW

            onClick {
                enabled = false
                text = "LOADING..."
                game.reset()
                sceneContainer.changeTo<PlayScene>()
            }
        }
    }
}
