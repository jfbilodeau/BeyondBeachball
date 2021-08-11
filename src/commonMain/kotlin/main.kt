import com.soywiz.korge.Korge

suspend fun main() = Korge(Korge.Config(GameModule()))

//suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
//	val minDegrees = (-16).degrees
//	val maxDegrees = (+16).degrees
//
//	val image = image(resourcesVfs["korge.png"].readBitmap()) {
//		rotation = maxDegrees
//		anchor(.5, .5)
//		scale(.8)
//		position(256, 256)
//	}
//
//	while (true) {
//		image.tween(image::rotation[minDegrees], time = 1.seconds, easing = Easing.EASE_IN_OUT)
//		image.tween(image::rotation[maxDegrees], time = 1.seconds, easing = Easing.EASE_IN_OUT)
//	}
//}