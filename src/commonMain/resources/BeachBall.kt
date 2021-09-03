class BeachBall(playField: PlayField, game: Game) : Sprite() {
    var contact = false

    init {
        name = "beachball"
        anchor(0.5, 0.5)
        hitShape {
            circle(width / 2.0, height / 2.0, width / 2.0)
        }
        val body = playField.createBody {
            type = BodyType.DYNAMIC
        }.fixture {
            shape = CircleShape(width / 2 / 20)
            density = 1.0f
            restitution = 0.5f
            friction = 0.1f
        }

        body.view = this
        this.body = body
    }
}