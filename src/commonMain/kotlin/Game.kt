import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class Resources {
    lateinit var beachball: Bitmap
    lateinit var leftFlipper: Bitmap
    lateinit var rightFlipper: Bitmap

    suspend fun load() {
        beachball = resourcesVfs["beachball.png"].readBitmap()
        leftFlipper = resourcesVfs["left-flipper-200.png"].readBitmap()
        rightFlipper = resourcesVfs["right-flipper-200.png"].readBitmap()
    }
}

class Game {
    val resources = Resources()
    val mapper = gameObjectMapper()
    var factory = EntityFactory()
}