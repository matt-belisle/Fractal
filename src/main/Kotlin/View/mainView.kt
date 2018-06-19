package View

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.ImageView
import javafx.scene.input.MouseButton
import javafx.scene.layout.VBox
import javafx.stage.Stage
import tornadofx.add

class mainView: Application() {

    private val height = 1000.0
    private val width = height
    val makeFractal = MakeImage(height.toInt())
    val imageView = ImageView(makeFractal.writePixels())


    override fun start(primaryStage: Stage) {
        imageView.setOnMouseClicked {
            var zoomIn = true
            if(it.button == MouseButton.SECONDARY){
                zoomIn = false
            }
            makeFractal.zoom(it.sceneX, it.sceneY, zoomIn)
            imageView.image = makeFractal.writePixels()
        }
        val changeFractal = TextBoxWithLabel()
        val vbox = VBox(changeFractal.vBox)

        vbox.add(imageView)

        val scene = Scene(vbox, width, height)
        primaryStage.scene = scene
        primaryStage.title = "Mandelbrot Black and White"
        primaryStage.show()

    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
           launch(mainView::class.java)
        }
    }
}
