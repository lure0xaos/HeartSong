package gargoyle.heartsong.controllers.app;

import gargoyle.heartsong.config.MediaConfig;
import gargoyle.heartsong.err.ItemNotFoundException;
import gargoyle.heartsong.model.app.Album;
import gargoyle.heartsong.model.app.Band;
import gargoyle.heartsong.model.app.Track;
import gargoyle.heartsong.services.AppService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/media")
@RequiredArgsConstructor
@Slf4j
public class MediaController {
    private final MediaConfig config;
    private final AppService service;

    @GetMapping("/thumbnail/band/{bandId}")
    public ResponseEntity<? extends Resource> getBandThumbnail(@PathVariable("bandId") Long bandId) {
        Band band = service.findBandById(bandId).orElseThrow(() -> new ItemNotFoundException("band", bandId));
        if (null == band.getThumbnailPath()) return getMissingImage();
        return ResponseEntity.ok()
                .contentType(band.getThumbnailType())
                .contentLength(band.getThumbnailContentLength())
                .body(findResource(band.getThumbnailPath()));
    }

    @GetMapping("/image/band/{bandId}")
    public ResponseEntity<? extends Resource> getBandImage(@PathVariable("bandId") Long bandId) {
        Band band = service.findBandById(bandId).orElseThrow(() -> new ItemNotFoundException("band", bandId));
        if (null == band.getImagePath()) return getMissingImage();
        return ResponseEntity.ok()
                .contentType(band.getImageType())
                .contentLength(band.getImageContentLength())
                .body(findResource(band.getImagePath()));
    }

    @GetMapping("/thumbnail/album/{albumId}")
    public ResponseEntity<? extends Resource> getAlbumThumbnail(@PathVariable("albumId") Long albumId) {
        Album album = service.findAlbumById(albumId).orElseThrow(() -> new ItemNotFoundException("album", albumId));
        if (null == album.getThumbnailPath()) return getMissingImage();
        return ResponseEntity.ok()
                .contentType(album.getThumbnailType())
                .contentLength(album.getThumbnailContentLength())
                .body(findResource(album.getThumbnailPath()));
    }

    @GetMapping("/image/album/{albumId}")
    public ResponseEntity<? extends Resource> getAlbumImage(@PathVariable("albumId") Long albumId) {
        Album album = service.findAlbumById(albumId).orElseThrow(() -> new ItemNotFoundException("album", albumId));
        if (null == album.getImagePath()) return getMissingImage();
        return ResponseEntity.ok()
                .contentType(album.getImageType())
                .contentLength(album.getImageContentLength())
                .body(findResource(album.getImagePath()));
    }

    @GetMapping("/audio/track/{trackId}")
    public ResponseEntity<? extends Resource> getTrackAudio(@PathVariable("trackId") Long trackId) {
        Track track = service.findTrackById(trackId).orElseThrow(() -> new ItemNotFoundException("track", trackId));
        if (null == track.getAudioPath()) return getMissingImage();
        return ResponseEntity.ok()
                .contentType(track.getAudioType())
                .contentLength(track.getAudioContentLength())
                .body(findResource(track.getAudioPath()));
    }

    @GetMapping("/video/track/{trackId}")
    public ResponseEntity<? extends Resource> getTrackVideo(@PathVariable("trackId") Long trackId) {
        Track track = service.findTrackById(trackId).orElseThrow(() -> new ItemNotFoundException("track", trackId));
        if (null == track.getVideoPath()) return getMissingImage();
        return ResponseEntity.ok()
                .contentType(track.getVideoType())
                .contentLength(track.getVideoContentLength())
                .body(findResource(track.getVideoPath()));
    }

    private ResponseEntity<? extends Resource> getMissingImage() {
        Resource resource = config.getMissingImage();
        try {
            Path path = Paths.get(resource.getURI());
            return ResponseEntity.ok()
                    .contentType(MediaType.asMediaType(MimeType.valueOf(Files.probeContentType(path))))
                    .contentLength(Files.size(path))
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.ok(resource);
        }
    }

    private Resource findResource(String path) {
        try {
            Resource resource = config.getMediaRoot().createRelative(path);
            if (resource.exists() && resource.isReadable()) return resource;
            log.error(path, new ItemNotFoundException("resource", path));
            return config.getMissingImage();
        } catch (IOException e) {
            log.error(path, new ItemNotFoundException("resource", path, e));
            return config.getMissingImage();
        }
    }
}
