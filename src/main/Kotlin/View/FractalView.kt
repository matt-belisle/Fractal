package View

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.stage.Stage

class FractalView: Application() {

    private val height = 500.0
    private val width = height
    val makeFractal = MakeImage()
    val imageView = ImageView(makeFractal.writePixels())

    override fun start(primaryStage: Stage) {
        val hbox = HBox(imageView)
        val scene = Scene(hbox, width, height)
        primaryStage.scene = scene
        primaryStage.title = "Mandelbrot Black and White"
        primaryStage.show()

    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
           launch(FractalView::class.java)
        }
    }
}
