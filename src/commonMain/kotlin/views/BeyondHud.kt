package views

import com.soywiz.korge.view.Container
import com.soywiz.korge.view.addUpdater
import com.soywiz.korge.view.text
import scenes.CodeScene

class BeyondHud(val codeScene: CodeScene): Container() {
    init {
        val text = text("")
        addUpdater {
            text.text = "Tokens: ${codeScene.pickupCount}/${codeScene.tokenCount}, ${codeScene.button.weight}"
        }
    }
}