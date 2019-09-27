package gargoyle.heartsong.controllers.admin;

import gargoyle.heartsong.err.ItemNotFoundException;
import gargoyle.heartsong.model.app.Album;
import gargoyle.heartsong.model.app.Band;
import gargoyle.heartsong.model.app.Track;
import gargoyle.heartsong.services.admin.AdminService;
import gargoyle.heartsong.services.admin.AdminUploadService;
import gargoyle.heartsong.util.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping(AdminTrackController.PATH_ROOT)
@RequiredArgsConstructor
public class AdminTrackController {

    public static final String PATH_ROOT = "/admin/track/{albumId}";
    private static final String PATH_INDEX = "/";
    private static final String PATH_CREATE = "/create";
    private static final String PATH_EDIT = "/edit/";
    private static final String PATH_EDIT_W = "/edit/{trackId}";
    private static final String PATH_DELETE_W = "/delete/{trackId}";

    private static final String PARAM_ALBUM_ID = "albumId";
    private static final String PARAM_TRACK_ID = "trackId";
    private static final String PARAM_AUDIO = "audio";
    private static final String PARAM_VIDEO = "video";

    private static final String VIEW_ADMIN_TRACK_INDEX = "admin/track/index";
    private static final String VIEW_ADMIN_TRACK_EDIT = "admin/track/edit";

    private static final String MODEL_BAND = "band";
    private static final String MODEL_ALBUM = "album";
    private static final String MODEL_TRACKS = "tracks";
    private static final String MODEL_TRACK = "track";

    private static final String MAPPING_INDEX = "admin.track.index";
    private static final String MAPPING_CREATE = "admin.track.create";
    private static final String MAPPING_EDIT = "admin.track.edit";

    private static final String TYPE_BAND = "band";
    private static final String TYPE_ALBUM = "album";
    private static final String TYPE_TRACK = "track";

    private final AdminService service;
    private final AdminUploadService uploadService;

    @GetMapping(name = MAPPING_INDEX, value = PATH_INDEX)
    public ModelAndView getIndex(@PathVariable(PARAM_ALBUM_ID) Long albumId) {
        Album album = service.findAlbumById(albumId).orElseThrow(() -> new ItemNotFoundException(TYPE_ALBUM, albumId));
        Long bandId = album.getBandId();
        Band band = service.findBandById(bandId).orElseThrow(() -> new ItemNotFoundException(TYPE_BAND, bandId));
        List<Track> tracks = service.findTracksByAlbumId(albumId);
        return new ModelAndView(VIEW_ADMIN_TRACK_INDEX, Maps.of(
                MODEL_BAND, band,
                MODEL_ALBUM, album,
                MODEL_TRACKS, tracks
        ));
    }

    @GetMapping(name = MAPPING_CREATE, value = PATH_CREATE)
    public ModelAndView getCreate(@PathVariable(PARAM_ALBUM_ID) Long albumId) {
        Album album = service.findAlbumById(albumId).orElseThrow(() -> new ItemNotFoundException(TYPE_ALBUM, albumId));
        Long bandId = album.getBandId();
        Band band = service.findBandById(bandId).orElseThrow(() -> new ItemNotFoundException(TYPE_BAND, bandId));
        Track track = Track.builder().albumId(albumId).build();
        return new ModelAndView(VIEW_ADMIN_TRACK_EDIT, Maps.of(
                MODEL_BAND, band,
                MODEL_ALBUM, album,
                MODEL_TRACK, track
        ));
    }

    @GetMapping(name = MAPPING_EDIT, value = PATH_EDIT_W)
    public ModelAndView getEdit(@PathVariable(PARAM_ALBUM_ID) Long albumId, @PathVariable(PARAM_TRACK_ID) Long trackId) {
        Album album = service.findAlbumById(albumId).orElseThrow(() -> new ItemNotFoundException(TYPE_ALBUM, albumId));
        Long bandId = album.getBandId();
        Band band = service.findBandById(bandId).orElseThrow(() -> new ItemNotFoundException(TYPE_BAND, bandId));
        Track track = service.findTrackById(trackId).orElseThrow(() -> new ItemNotFoundException(TYPE_TRACK, trackId));
        return new ModelAndView(VIEW_ADMIN_TRACK_EDIT, Maps.of(
                MODEL_BAND, band,
                MODEL_ALBUM, album,
                MODEL_TRACK, track
        ));
    }

    @PostMapping(PATH_EDIT)
    public String onCreate(@PathVariable(PARAM_ALBUM_ID) Long albumId, Track track) {
        service.saveTrack(track);
        Long trackId = track.getId();
//        return "redirect:/admin/track/" + albumId + "/edit/" + trackId;
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName(
                ServletUriComponentsBuilder.fromCurrentContextPath(), MAPPING_EDIT)
                .arg(0, albumId)
                .arg(1, trackId)
                .build();
    }

    @PostMapping(PATH_EDIT_W)
    public String onEdit(@PathVariable(PARAM_ALBUM_ID) Long albumId, @PathVariable(PARAM_TRACK_ID) Long trackId, Track track,
                         @RequestParam(value = PARAM_AUDIO, required = false) MultipartFile audio,
                         @RequestParam(value = PARAM_VIDEO, required = false) MultipartFile video) {
        uploadService.uploadTrackAudio(track, audio);
        uploadService.uploadTrackVideo(track, video);
        service.saveTrack(track);
        Long newTrackId = track.getId();
//        return "redirect:/admin/track/" + albumId + "/edit/" + newTrackId;
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName(
                ServletUriComponentsBuilder.fromCurrentContextPath(), MAPPING_EDIT)
                .arg(0, albumId)
                .arg(1, newTrackId)
                .build();
    }

    @PostMapping(PATH_DELETE_W)
    public String onDelete(@PathVariable(PARAM_ALBUM_ID) Long albumId, @PathVariable(PARAM_TRACK_ID) Long trackId) {
        service.deleteTrack(trackId);
//        return "redirect:/admin/track/" + albumId;
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName(
                ServletUriComponentsBuilder.fromCurrentContextPath(), MAPPING_INDEX)
                .arg(0, albumId)
                .build();
    }

}
