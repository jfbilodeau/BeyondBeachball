enum class EntityLayer {
    Background,
    Main,
    Foreground,
}

class LevelEntity {
    var type= ""
    var x = 0.0
    var y = 0.0
    var layer = EntityLayer.Main
    var width = 1.0
    var height = 1.0
    var param = ""
}