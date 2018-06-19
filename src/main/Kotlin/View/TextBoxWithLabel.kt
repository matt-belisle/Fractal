package View

import Data.Complex
import Events.ComplexEvent
import tornadofx.EventBus.RunOn.*
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import tornadofx.FX
import tornadofx.action
import tornadofx.add

class TextBoxWithLabel {
    lateinit var vBox: VBox
    private val realTextField = TextField()
    private val imagTextField = TextField()
    private val submitButton = Button()
    init{
        val imagLabel = Label()
        imagLabel.text = "Imaginary"

        submitButton.text = "Submit"

        submitButton.action {
                val cValue = Complex(
                try {
                    realTextField.text.toDouble()
                } catch (e: NumberFormatException){
                    0.0
                },
                try {
                    imagTextField.text.toDouble()
                } catch(e: NumberFormatException){
                0.0
                })
            FX.eventbus.fire(ComplexEvent(cValue))
        }
        val realhBox = HBox()
        realhBox.add(Label("Real"))
        realhBox.add(realTextField)

        val imaghBox = HBox()
        imaghBox.add(Label("Imaginary"))
        imaghBox.add(imagTextField)

        vBox.add(realhBox)
        vBox.add(imaghBox)
        vBox.add(submitButton)

    }
}