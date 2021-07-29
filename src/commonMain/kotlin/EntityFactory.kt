class EntityFactory {
    var entityTypes = listOf<EntityType>(
        EntityType("nebula").apply { factory="nebula" }
    )

    fun entityType(name: String): EntityType {
        return entityTypes.first { it.name == name }
    }

    fun createEntity(levelEntity: LevelEntity): Entity {
        val entityType = entityType(levelEntity.type)

        val entity = when (entityType.factory) {
            "nebula" -> { Entity() }
            else -> { throw Exception("Unexpected entity type: ${entityType.factory}")}
        }

        entity.type = levelEntity.type

        return entity
    }
}