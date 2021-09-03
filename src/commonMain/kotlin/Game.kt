import com.soywiz.klock.TimeSpan
import com.soywiz.klock.seconds
import com.soywiz.korau.sound.Sound
import com.soywiz.korau.sound.readSound
import com.soywiz.korge.view.SpriteAnimation
import com.soywiz.korge.view.Views
import com.soywiz.korim.bitmap.Bitmap
import com.soywiz.korim.format.readBitmap
import com.soywiz.korio.file.std.resourcesVfs
import com.soywiz.korma.geom.Point
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class Resources {
    lateinit var coin: SpriteAnimation
    lateinit var beachball: Bitmap
    lateinit var leftFlipper: Bitmap
    lateinit var rightFlipper: Bitmap

    lateinit var bounceSound1: Sound
    lateinit var bounceSound2: Sound
    lateinit var bounceSound3: Sound
    lateinit var bumberSound1: Sound
    lateinit var bumberSound2: Sound
    lateinit var bumberSound3: Sound
    lateinit var mouseSound: Sound
    lateinit var coinSound: Sound
    lateinit var fishSound: Sound
    lateinit var hornSound: Sound
    lateinit var springSound: Sound
    lateinit var debrisSound: Sound

    suspend fun load() {
        coin = SpriteAnimation(resourcesVfs["coin-100.png"].readBitmap(), 100, 100, columns = 6)
        beachball = resourcesVfs["beachball.png"].readBitmap()
        leftFlipper = resourcesVfs["left-flipper-200.png"].readBitmap()
        rightFlipper = resourcesVfs["right-flipper-200.png"].readBitmap()

        bounceSound1 = resourcesVfs["bounce-1.mp3"].readSound()
        bounceSound2 = resourcesVfs["bounce-2.mp3"].readSound()
        bounceSound3 = resourcesVfs["bounce-3.mp3"].readSound()
        bumberSound1 = resourcesVfs["bounce-1.mp3"].readSound()
        bumberSound2 = resourcesVfs["bounce-2.mp3"].readSound()
        bumberSound3 = resourcesVfs["bounce-3.mp3"].readSound()
        mouseSound = resourcesVfs["mouse.mp3"].readSound()
        coinSound = resourcesVfs["coin.mp3"].readSound()
        fishSound = resourcesVfs["bubbles.mp3"].readSound()
        hornSound = resourcesVfs["car-horn.mp3"].readSound()
        springSound = resourcesVfs["spring.mp3"].readSound()
        debrisSound = resourcesVfs["debris.mp3"].readSound()
    }
}

class Level(val name: String, val intro: String, val ktree: String, val background: String, val start: Point)
class BeyondLevel @OptIn(ExperimentalTime::class) constructor(val filename: String, val description: String, duration: TimeSpan)

class Game {
    lateinit var views: Views
    val stageWidth = 1280
    val stageHeight = 768

    var currentLevelIndex = 0
    var currentBeyondLevelIndex = 0
    var maxCoins = 0
    var coins = 0
    val allCoinsCollected
        get() = coins == maxCoins
    var touchedExit = false

    val levels = listOf(
//        Level("Sewers", "sewer-intro.ktree", "sewers.ktree", "sewers-background.png", Point(-520.0, -2300.0)),
        Level("Sewers", "sewer-intro.ktree", "sewers.ktree", "sewers-background.png", Point(220.0, 0.0)),
        Level("City", "city-intro.ktree", "city.ktree", "city-background.png", Point(0.0, 0.0)),
        Level("Beach", "beach-intro.ktree", "beach.ktree", "beach-background.png", Point(1470, -2400)),
    )

    val beyondLevels = listOf(
        BeyondLevel("tutorial.txt", "Follow the instructions. Ya can't go wrong", TimeSpan.NIL),
        BeyondLevel("hello-world.kt", "Every programmer begins here...", 60.seconds),
        BeyondLevel("Flipper.kt", "Every programmer begins here...", 60.seconds),
        BeyondLevel("BeachBall.kt", "Every programmer begins here...", 60.seconds),
        BeyondLevel("IntroScene.kt", "Every programmer begins here...", 60.seconds),
    )

    val currentLevel
        get() = levels[currentLevelIndex]

    val resources = Resources()

    fun reset() {
        maxCoins = 0
        coins = 0
        touchedExit = false
    }
}