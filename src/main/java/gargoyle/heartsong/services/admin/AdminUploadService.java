package gargoyle.heartsong.services.admin;

import gargoyle.heartsong.config.MediaConfig;
import gargoyle.heartsong.model.app.Album;
import gargoyle.heartsong.model.app.Band;
import gargoyle.heartsong.model.app.Track;
import gargoyle.heartsong.util.Images;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminUploadService {
    private static final String TYPE_BAND = "band";
    private static final String TYPE_ALBUM = "album";
    private static final String TYPE_TRACK = "track";
    private static final String TYPE_IMAGE = "image";
    private static final String TYPE_THUMB = "thumb";
    private static final String TYPE_AUDIO = "audio";
    private static final String TYPE_VIDEO = "video";
    private final MediaConfig config;
    private Path rootPath;

    @PostConstruct
    public void init() throws IOException {
        rootPath = config.getMediaRoot().getFile().toPath();
        if (!Files.exists(rootPath)) Files.createDirectories(rootPath);
    }

    public void uploadBandImage(Band band, MultipartFile file) {
        if (file.isEmpty()) return;
        try {
            BufferedImage original = Images.load(file);
            String originalFilename = file.getOriginalFilename();
            {
                BufferedImage image = Images.fit(original, config.getImageWidth(), config.getImageHeight());
                Path relativePath = Paths.get(String.join("_", TYPE_BAND, TYPE_IMAGE, originalFilename));
                Path absolutePath = rootPath.resolve(relativePath);

                MediaType originalType = MediaType.valueOf(Objects.requireNonNull(file.getContentType()));
                Images.saveImage(image, absolutePath, originalType);

                band.setImagePath(relativePath.toString());
                band.setImageContentLength(Files.size(absolutePath));
                band.setImageType(originalType);
                band.setImageWidth(image.getWidth());
                band.setImageHeight(image.getHeight());
            }
            {
                BufferedImage thumbnail = Images.fit(original, config.getThumbnailWidth(), config.getThumbnailHeight());
                Path relativePath = Paths.get(String.join("_", TYPE_BAND, TYPE_THUMB, originalFilename));
                Path absolutePath = rootPath.resolve(relativePath);

                MediaType originalType = MediaType.valueOf(Objects.requireNonNull(file.getContentType()));
                Images.saveImage(thumbnail, absolutePath, originalType);

                band.setThumbnailPath(relativePath.toString());
                band.setThumbnailContentLength(Files.size(absolutePath));
                band.setThumbnailType(originalType);
                band.setThumbnailWidth(thumbnail.getWidth());
                band.setThumbnailHeight(thumbnail.getHeight());
            }
        } catch (IOException e) {
            log.error("upload", e);
        }
    }

    public void uploadAlbumImage(Album album, MultipartFile file) {
        if (file.isEmpty()) return;
        try {
            BufferedImage original = Images.load(file);
            String originalFilename = file.getOriginalFilename();
            {
                BufferedImage image = Images.fit(original, config.getImageWidth(), config.getImageHeight());
                Path relativePath = Paths.get(String.join("_", TYPE_ALBUM, TYPE_IMAGE, originalFilename));
                Path absolutePath = rootPath.resolve(relativePath);

                MediaType originalType = MediaType.valueOf(Objects.requireNonNull(file.getContentType()));
                Images.saveImage(image, absolutePath, originalType);

                album.setImagePath(relativePath.toString());
                album.setImageContentLength(Files.size(absolutePath));
                album.setImageType(originalType);
                album.setImageWidth(image.getWidth());
                album.setImageHeight(image.getHeight());
            }
            {
                BufferedImage thumbnail = Images.fit(original, config.getThumbnailWidth(), config.getThumbnailHeight());
                Path relativePath = Paths.get(String.join("_", TYPE_ALBUM, TYPE_THUMB, originalFilename));
                Path absolutePath = rootPath.resolve(relativePath);

                MediaType originalType = MediaType.valueOf(Objects.requireNonNull(file.getContentType()));
                Images.saveImage(thumbnail, absolutePath, originalType);

                album.setThumbnailPath(relativePath.toString());
                album.setThumbnailContentLength(Files.size(absolutePath));
                album.setThumbnailType(originalType);
                album.setThumbnailWidth(thumbnail.getWidth());
                album.setThumbnailHeight(thumbnail.getHeight());
            }
        } catch (IOException e) {
            log.error("upload", e);
        }
    }

    public void uploadTrackAudio(Track track, MultipartFile file) {
        if (file.isEmpty()) return;
        try {
            Path relativePath = Paths.get(String.join("_", TYPE_TRACK, TYPE_AUDIO, file.getOriginalFilename()));
            Path absolutePath = rootPath.resolve(relativePath);
            file.transferTo(absolutePath);
            MediaType originalType = MediaType.valueOf(Objects.requireNonNull(file.getContentType()));
            track.setAudioPath(relativePath.toString());
            track.setAudioContentLength(Files.size(absolutePath));
            track.setAudioType(originalType);
            track.setAudioLength(null);
            track.setAudioBitrate(0);
        } catch (IOException e) {
            log.error("upload", e);
        }
    }

    public void uploadTrackVideo(Track track, MultipartFile file) {
        if (file.isEmpty()) return;
        try {
            Path relativePath = Paths.get(String.join("_", TYPE_TRACK, TYPE_VIDEO, file.getOriginalFilename()));
            Path absolutePath = rootPath.resolve(relativePath);
            file.transferTo(absolutePath);
            MediaType originalType = MediaType.valueOf(Objects.requireNonNull(file.getContentType()));
            track.setVideoPath(relativePath.toString());
            track.setVideoContentLength(Files.size(absolutePath));
            track.setVideoType(originalType);
            track.setVideoLength(null);
            track.setVideoWidth(0);
            track.setVideoHeight(0);
        } catch (IOException e) {
            log.error("upload", e);
        }
    }

}
