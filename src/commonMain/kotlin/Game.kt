import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs

class Resources {
    lateinit var beachball: Bitmap

    suspend fun load() {
        beachball = resourcesVfs["beachball.png"].readBitmap()
    }
}

class Game {
    val resources = Resources()
}