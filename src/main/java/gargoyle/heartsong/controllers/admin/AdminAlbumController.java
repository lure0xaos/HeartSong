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

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(AdminAlbumController.PATH_ROOT)
@RequiredArgsConstructor
public class AdminAlbumController {

    public static final String PATH_ROOT = "/admin/album/{bandId}";
    private static final String PATH_INDEX = "/";
    private static final String PATH_CREATE = "/create";
    private static final String PATH_EDIT = "/edit/";
    private static final String PATH_EDIT_W = "/edit/{albumId}";
    private static final String PATH_DELETE_W = "/delete/{albumId}";

    private static final String PARAM_BAND_ID = "bandId";
    private static final String PARAM_ALBUM_ID = "albumId";
    private static final String PARAM_IMAGE = "image";

    private static final String VIEW_ADMIN_ALBUM_INDEX = "admin/album/index";
    private static final String VIEW_ADMIN_ALBUM_EDIT = "admin/album/edit";

    private static final String MODEL_BAND = "band";
    private static final String MODEL_ALBUM = "album";
    private static final String MODEL_ALBUMS = "albums";
    private static final String MODEL_TRACKS = "tracks";

    private static final String MAPPING_INDEX = "admin.album.index";
    private static final String MAPPING_CREATE = "admin.album.create";
    private static final String MAPPING_EDIT = "admin.album.edit";

    private static final String TYPE_BAND = "band";
    private static final String TYPE_ALBUM = "album";

    private final AdminService service;
    private final AdminUploadService uploadService;

    @GetMapping(name = MAPPING_INDEX, value = PATH_INDEX)
    public ModelAndView getIndex(@PathVariable(PARAM_BAND_ID) Long bandId) {
        Band band = service.findBandById(bandId).orElseThrow(() -> new ItemNotFoundException(TYPE_BAND, bandId));
        List<Album> albums = service.findAlbumsByBandId(bandId);
        return new ModelAndView(VIEW_ADMIN_ALBUM_INDEX, Maps.of(
                MODEL_BAND, band,
                MODEL_ALBUMS, albums
        ));
    }

    @GetMapping(name = MAPPING_CREATE, value = PATH_CREATE)
    public ModelAndView getCreate(@PathVariable(PARAM_BAND_ID) Long bandId) {
        Band band = service.findBandById(bandId).orElseThrow(() -> new ItemNotFoundException(TYPE_BAND, bandId));
        Album album = Album.builder().bandId(bandId).build();
        List<Object> tracks = Collections.emptyList();
        return new ModelAndView(VIEW_ADMIN_ALBUM_EDIT, Maps.of(
                MODEL_BAND, band,
                MODEL_ALBUM, album,
                MODEL_TRACKS, tracks
        ));
    }

    @GetMapping(name = MAPPING_EDIT, value = PATH_EDIT_W)
    public ModelAndView getEdit(@PathVariable(PARAM_BAND_ID) Long bandId, @PathVariable(PARAM_ALBUM_ID) Long albumId) {
        Band band = service.findBandById(bandId).orElseThrow(() -> new ItemNotFoundException(TYPE_BAND, bandId));
        Album album = service.findAlbumById(albumId).orElseThrow(() -> new ItemNotFoundException(TYPE_ALBUM, albumId));
        List<Track> tracks = service.findTracksByAlbumId(albumId);
        return new ModelAndView(VIEW_ADMIN_ALBUM_EDIT, Maps.of(
                MODEL_BAND, band,
                MODEL_ALBUM, album,
                MODEL_TRACKS, tracks
        ));
    }

    @PostMapping(PATH_EDIT)
    public String onCreate(@PathVariable(PARAM_BAND_ID) Long bandId, Album album) {
        service.saveAlbum(album);
        Long albumId = album.getId();
//        return "redirect:/admin/album/" + bandId + "/edit/" + albumId;
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName(
                ServletUriComponentsBuilder.fromCurrentContextPath(), MAPPING_EDIT)
                .arg(0, bandId)
                .arg(1, albumId)
                .build();
    }

    @PostMapping(PATH_EDIT_W)
    public String onEdit(@PathVariable(PARAM_BAND_ID) Long bandId, @PathVariable(PARAM_ALBUM_ID) Long albumId,
                         @RequestParam(value = PARAM_IMAGE, required = false) MultipartFile image, Album album) {
        uploadService.uploadAlbumImage(album, image);
        service.saveAlbum(album);
        Long newAlbumId = album.getId();
//        return "redirect:/admin/album/" + bandId + "/edit/" + newAlbumId;

        return "redirect:" + MvcUriComponentsBuilder.fromMappingName(
                ServletUriComponentsBuilder.fromCurrentContextPath(), MAPPING_EDIT)
                .arg(0, bandId)
                .arg(1, newAlbumId)
                .build();
    }

    @PostMapping(PATH_DELETE_W)
    public String onDelete(@PathVariable(PARAM_BAND_ID) Long bandId, @PathVariable(PARAM_ALBUM_ID) Long albumId) {
        service.deleteAlbum(albumId);
//        return "redirect:/admin/album/" + bandId;
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName(
                ServletUriComponentsBuilder.fromCurrentContextPath(), MAPPING_INDEX)
                .arg(0, bandId)
                .build();
    }

}
