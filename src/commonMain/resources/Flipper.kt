package entities

class LeftFlipper(container: Container, game: Game) : Sprite() {
    init {
//        anchor(20, bitmap.height / 2)
        anchorY = 0.5
        rotation = 30.0.degrees
        val body = container.createBody {
            type = BodyType.KINEMATIC
        }.fixture {
            shape = BoxShape(bitmap.width / 20, bitmap.height / 20)
            density = 1f
            restitution = 1f
        }

        body.view = this
    }
}

