class LevelEntity {
    var id = 0
    var type = ""
    var x = 0.0
    var y = 0.0
}

class Level {
    private var nextId = 1
    private val entities = listOf<LevelEntity>()

    fun entityById(id: Int): LevelEntity? {
        return entities.firstOrNull() { it.id == id }
    }

    fun nextId(): Int {
        while (entities.firstOrNull() { it.id == nextId } != null) {
            nextId++
        }

        return nextId
    }
}
