package View

import Events.ComplexEvent
import javafx.scene.Parent
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.scene.layout.VBox
import tornadofx.FX
import tornadofx.View


class FractalImageView( height: Double) : View(){
    override val root = VBox()
    val makeFractal = MakeImage(height.toInt())
    init {
    val imageView = ImageView()
       imageView.setOnMouseClicked {
            var zoomIn = true
            if (it.button == MouseButton.SECONDARY) {
                zoomIn = false
            }
            makeFractal.zoom(it.sceneX, it.sceneY, zoomIn)
            imageView.image = makeFractal.writePixels()
        }
        root.add(imageView)
        subscribe<ComplexEvent> { print("hi") }
    }
    val listenForNewComplex: (ComplexEvent) -> Unit = {
           // makeFractal.newFractal(it.complex)
    }
}