import com.soywiz.korge.view.Container
import com.soywiz.korge.view.container

class LevelView(val game: Game) : Container() {
    private var nextId = 1
    private val entities = mutableListOf<Entity>()
    private val addedEntities = mutableListOf<Entity>()
    private val removedEntities = mutableListOf<Entity>()

    private val background = container()
    private val main = container()
    private val foreground = container()

    init {

    }

    fun update() {
        for (entity in entities) {
            entity.update()
        }

        processAddedAndRemovedEntities()
    }

    fun processAddedAndRemovedEntities() {
        for (entity in addedEntities) {
            entities.add(entity)
        }

        addedEntities.clear()

        for (entity in removedEntities) {
            entities.remove(entity)
        }

        removedEntities.clear()
    }

    fun entityById(id: Int): Entity? {
        return entities.firstOrNull() { it.id == id }
    }

    private fun nextId(): Int {
        while (entities.firstOrNull() { it.id == nextId } != null) {
            nextId++
        }

        return nextId
    }

    fun addEntity(entity: Entity): Int {
        val entityId = nextId()

        entity.id = entityId

        addedEntities.add(entity)

        return entityId
    }

    fun removeEntity(entity: Entity) {
        removedEntities.add(entity)
    }

    fun clearEntities() {
        removedEntities.addAll(entities)
    }

    fun loadLevel(level: Level) {
        for (levelEntity in level.entities) {
            val entity = game.factory.createEntity(levelEntity)
            addEntity(entity)
        }
    }
}
