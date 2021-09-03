package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.text
import scenes.CodeScene

class BeyondHud(val codeScene: CodeScene): Container() {
    init {
        val text = text("")
        addUpdater {
            text.text = "Tokens collected: ${codeScene.pickupCount}/${codeScene.tokenCount}   Bug quashed: ${codeScene.button.weight.toInt() - 12}"
        }
    }
}