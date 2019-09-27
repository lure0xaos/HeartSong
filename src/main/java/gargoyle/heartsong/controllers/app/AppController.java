package gargoyle.heartsong.controllers.app;

import gargoyle.heartsong.annotations.AlertFailure;
import gargoyle.heartsong.annotations.ExceptionAlert;
import gargoyle.heartsong.err.ItemNotFoundException;
import gargoyle.heartsong.model.app.Album;
import gargoyle.heartsong.model.app.Band;
import gargoyle.heartsong.model.app.SearchResult;
import gargoyle.heartsong.services.AppService;
import gargoyle.heartsong.util.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;

@Controller
@RequestMapping(AppController.PATH_ROOT)
@SessionAttributes(names = {AppController.PARAM_QUERY, AppController.PARAM_RESULTS})
@RequiredArgsConstructor
public class AppController {
    public static final String PATH_ROOT = "/";
    public static final String PARAM_QUERY = "query";
    public static final String PARAM_RESULTS = "results";
    private static final String PATH_LETTER_W = "/{letter:[#A-Za-z]}";
    private static final String PATH_BAND_W_W = "/{letter:[#A-Za-z]}/{bandAlias}";
    private static final String PATH_ALBUM_W_W_W = "/{letter:[#A-Za-z]}/{bandAlias}/{albumAlias}";
    private static final String PATH_SEARCH = "/search";
    private static final String PARAM_LETTER = "letter";
    private static final String PARAM_BAND_ALIAS = "bandAlias";
    private static final String PARAM_ALBUM_ALIAS = "albumAlias";
    private static final String MODEL_BANDS = "bands";
    private static final String MODEL_LETTER = "letter";
    private static final String MODEL_BAND = "band";
    private static final String MODEL_ALBUMS = "albums";
    private static final String MODEL_ALBUM = "album";
    private static final String MODEL_TRACKS = "tracks";
    private static final String MODEL_QUERY = "query";
    private static final String MODEL_RESULTS = "model";
    private static final String VIEW_APP_INDEX = "app/index";
    private static final String VIEW_APP_LETTER = "app/letter";
    private static final String VIEW_APP_BAND = "app/band";
    private static final String VIEW_APP_ALBUM = "app/album";
    private static final String VIEW_APP_SEARCH = "app/search";
    private static final String TYPE_BAND = "band";
    private static final String TYPE_ALBUM = "album";
    private static final String METHOD_SEARCH = "onSearch";
    private static final String MAPPING_SEARCH = "search";
    private static final String MAPPING_LETTER = "letter";
    private static final String MAPPING_INDEX = "index";
    private static final String MAPPING_BAND = "band";
    private static final String MAPPING_ALBUM = "album";

    private final AppService service;

    @ModelAttribute(PARAM_QUERY)
    public String createQuery() {
        return "";
    }

    @ModelAttribute(PARAM_RESULTS)
    public ArrayList<SearchResult<?>> createResults() {
        return new ArrayList<>();
    }

    @GetMapping(name = MAPPING_INDEX, value = PATH_ROOT)
    public ModelAndView getIndex() {
        return new ModelAndView(VIEW_APP_INDEX);
    }

    @RequestMapping("/err")
    @ExceptionAlert(failure = @AlertFailure(returning = "${new org.springframework.web.servlet.ModelAndView(\"app/index\")}"))
    public ModelAndView getError() {
        throw new RuntimeException("test", new RuntimeException("test cause"));
    }

    @GetMapping(name = MAPPING_LETTER, value = PATH_LETTER_W)
    public ModelAndView getLetter(@PathVariable(PARAM_LETTER) String letter) {
        return new ModelAndView(VIEW_APP_LETTER, Maps.of(
                MODEL_LETTER, letter,
                MODEL_BANDS, service.findBandsByLetter(letter)));
    }

    @GetMapping(name = MAPPING_BAND, value = PATH_BAND_W_W)
    public ModelAndView getBand(@PathVariable(PARAM_LETTER) String letter, @PathVariable(PARAM_BAND_ALIAS) String bandAlias) {
        Band band = service.findBandByAlias(bandAlias).orElseThrow(() -> new ItemNotFoundException(TYPE_BAND, bandAlias));
        return new ModelAndView(VIEW_APP_BAND, Maps.of(
                MODEL_BAND, band,
                MODEL_ALBUMS, service.findAlbumsByBandId(band.getId())));
    }

    @GetMapping(name = MAPPING_ALBUM, value = PATH_ALBUM_W_W_W)
    public ModelAndView getAlbum(@PathVariable(PARAM_LETTER) String letter,
                                 @PathVariable(PARAM_BAND_ALIAS) String bandAlias,
                                 @PathVariable(PARAM_ALBUM_ALIAS) String albumAlias) {
        Band band = service.findBandByAlias(bandAlias).orElseThrow(() -> new ItemNotFoundException(MODEL_BAND, bandAlias));
        Album album = service.findAlbumByAlias(albumAlias, bandAlias).orElseThrow(() -> new ItemNotFoundException(TYPE_ALBUM, albumAlias));
        return new ModelAndView(VIEW_APP_ALBUM, Maps.of(
                MODEL_BAND, band,
                MODEL_ALBUM, album,
                MODEL_TRACKS, service.findTracksByAlbumId(album.getId())));
    }

    @PostMapping(PATH_SEARCH)
    public String onSearch(@RequestParam(PARAM_QUERY) String query, RedirectAttributes redirect) {
        redirect.addFlashAttribute(PARAM_QUERY, query);
        redirect.addFlashAttribute(PARAM_RESULTS, service.search(query));
//        return MvcUriComponentsBuilder.fromMethodName(AppController.class, METHOD_SEARCH, query, attr).toUriString();
        return "redirect:" + MvcUriComponentsBuilder.fromMappingName(
                ServletUriComponentsBuilder.fromCurrentContextPath(), MAPPING_SEARCH)
                .arg(0, query)
                .arg(1, redirect)
                .build();
    }

    @GetMapping(name = MAPPING_SEARCH, value = PATH_SEARCH)
    public Object getSearch(@ModelAttribute(PARAM_QUERY) String query,
                            @ModelAttribute(PARAM_RESULTS) ArrayList<SearchResult<?>> results,
                            RedirectAttributes redirect) {
        return new ModelAndView(VIEW_APP_SEARCH, Maps.of(MODEL_QUERY, query, MODEL_RESULTS, results));
    }

}
