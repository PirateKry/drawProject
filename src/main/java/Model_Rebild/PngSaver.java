package Model_Rebild;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PngSaver {

    public static void save(DrCanvas_R canvas, File file) throws IOException {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.WHITE);   // фон (по умолчанию прозрачный)
        WritableImage image = canvas.snapshot(params, null);
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
    }
}