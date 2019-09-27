package gargoyle.heartsong.controllers.admin;

import gargoyle.heartsong.err.ItemNotFoundException;
import gargoyle.heartsong.model.app.Album;
import gargoyle.heartsong.model.app.Band;
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
@RequestMapping(AdminBandController.PATH_ROOT)
@RequiredArgsConstructor
public class AdminBandController {

    public static final String PATH_ROOT = "/admin/band";
    private static final String PATH_INDEX = "/";
    private static final String PATH_CREATE = "/create";
    private static final String PATH_EDIT = "/edit/";
    private static final String PATH_EDIT_W = "/edit/{bandId}";
    private static final String PATH_DELETE_W = "/delete/{bandId}";

    private static final String PARAM_BAND_ID = "bandId";
    private static final String PARAM_IMAGE = "image";

    private static final String VIEW_ADMIN_BAND_INDEX = "admin/band/index";
    private static final String VIEW_ADMIN_BAND_EDIT = "admin/band/edit";

    private static final String MODEL_BAND = "band";
    private static final String MODEL_BANDS = "bands";
    private static final String MODEL_ALBUMS = "albums";

    private static final String MAPPING_INDEX = "admin.band.index";
    private static final String MAPPING_CREATE = "admin.band.create";
    private static final String MAPPING_EDIT = "admin.band.edit";

    private static final String TYPE_BAND = "band";

    private final AdminService service;
    private final AdminUploadService uploadService;

    @GetMapping(name = MAPPING_INDEX, value = PATH_INDEX)
    public ModelAndView getIndex() {
        List<Band> bands = service.getBands();
        return new ModelAndView(VIEW_ADMIN_BAND_INDEX, Maps.of(
                MODEL_BANDS, bands
        ));
    }

    @GetMapping(name = MAPPING_CREATE, value = PATH_CREATE)
    public ModelAndView getCreate() {
        Band band = Band.builder().build();
        List<Object> bands = Collections.emptyList();
        return new ModelAndView(VIEW_ADMIN_BAND_EDIT, Maps.of(
                MODEL_BAND, band,
                MODEL_ALBUMS, bands
        ));
    }

    @GetMapping(name = MAPPING_EDIT, value = PATH_EDIT_W)
    public ModelAndView getEdit(@PathVariable(PARAM_BAND_ID) Long bandId) {
        Band band = service.findBandById(bandId).orElseThrow(() -> new ItemNotFoundException(TYPE_BAND, bandId));
        List<Album> albums = service.findAlbumsByBandId(bandId);
        return new ModelAndView(VIEW_ADMIN_BAND_EDIT, Maps.of(
                MODEL_BAND, band,
                MODEL_ALBUMS, albums
        ));
    }

    @PostMapping(PATH_EDIT)
    public String onCreate(Band band) {
        service.saveBand(band);
        Long bandId = band.getId();
//        return "redirect:/admin/band/edit/" + bandId;
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName(
                ServletUriComponentsBuilder.fromCurrentContextPath(), MAPPING_EDIT)
                .arg(0, bandId)
                .build();
    }

    @PostMapping(PATH_EDIT_W)
    public String onEdit(@PathVariable(PARAM_BAND_ID) Long bandId,
                         @RequestParam(value = PARAM_IMAGE, required = false) MultipartFile image, Band band) {
        uploadService.uploadBandImage(band, image);
        service.saveBand(band);
        Long newBandId = band.getId();
//        return "redirect:/admin/band/edit/" + newBandId;
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName(
                ServletUriComponentsBuilder.fromCurrentContextPath(), MAPPING_EDIT)
                .arg(0, newBandId)
                .build();
    }

    @PostMapping(PATH_DELETE_W)
    public String onDelete(@PathVariable(PARAM_BAND_ID) Long bandId) {
        service.deleteBand(bandId);
//        return "redirect:/admin/band/";
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName(
                ServletUriComponentsBuilder.fromCurrentContextPath(), MAPPING_INDEX)
                .build();
    }

}
