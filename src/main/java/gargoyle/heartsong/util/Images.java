package gargoyle.heartsong.util;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public final class Images {
    private Images() {
    }

    public static BufferedImage load(MultipartFile image) throws IOException {
        return ImageIO.read(image.getInputStream());
    }

    public static BufferedImage load(Path path) throws IOException {
        return ImageIO.read(path.toUri().toURL());
    }

    public static BufferedImage fit(BufferedImage source, int outerWidth, int outerHeight) {
        Dimension scaledDimension = getScaledDimension(new Dimension(source.getWidth(), source.getHeight()), new Dimension(outerWidth, outerHeight));
        return resizeImage(source, source.getType(), scaledDimension.width, scaledDimension.height);
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int imgWidth, int imgHeight) {
        BufferedImage resizedImage = new BufferedImage(imgWidth, imgHeight, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, imgWidth, imgHeight, null);
        g.dispose();
        return resizedImage;
    }

    public static Dimension getScaledDimension(Dimension inner, Dimension outer) {
        int fitWidth = inner.width;
        int fitHeight = inner.height;
        if (inner.width > outer.width) {
            fitWidth = outer.width;
            fitHeight = fitWidth * inner.height / inner.width;
        }
        if (fitHeight > outer.height) {
            fitHeight = outer.height;
            fitWidth = fitHeight * inner.width / inner.height;
        }
        return new Dimension(fitWidth, fitHeight);
    }

    public static void saveImage(BufferedImage image, Path path, MediaType contentType) throws IOException {
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByMIMEType(contentType.toString());
        if (writers.hasNext()) {
            ImageWriter writer = writers.next();
            try (Closeable output = ImageIO.createImageOutputStream(Files.newOutputStream(path))) {
                writer.setOutput(output);
                writer.write(image);
            }
        }
//        ImageIO.write(image, formatByType(contentType), Files.newOutputStream(path));
    }

//    private static String formatByType(MediaType contentType) {
//        if (Objects.equals(contentType, MediaType.IMAGE_PNG)) {
//            return "PNG";
//        }
//        if (Objects.equals(contentType, MediaType.IMAGE_GIF)) {
//            return "GIF";
//        }
//        if (Objects.equals(contentType, MediaType.IMAGE_JPEG)) {
//            return "JPEG";
//        }
//        throw new IllegalArgumentException(contentType.getType());
//    }
}
