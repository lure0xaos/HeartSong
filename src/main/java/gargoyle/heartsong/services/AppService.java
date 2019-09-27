package gargoyle.heartsong.services;

import gargoyle.heartsong.dao.app.AlbumDao;
import gargoyle.heartsong.dao.app.BandDao;
import gargoyle.heartsong.dao.app.TrackDao;
import gargoyle.heartsong.err.ItemNotFoundException;
import gargoyle.heartsong.model.app.Album;
import gargoyle.heartsong.model.app.Band;
import gargoyle.heartsong.model.app.SearchResult;
import gargoyle.heartsong.model.app.Track;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppService {
    private final BandDao bandDao;
    private final AlbumDao albumDao;
    private final TrackDao trackDao;

    public List<Band> findBandsByLetter(String letter) {
        return bandDao.findAllByLetter(letter);
    }

    public Optional<Band> findBandByAlias(String bandAlias) {
        return bandDao.findByAlias(bandAlias);
    }

    public Optional<Band> findBandById(Long id) {
        return bandDao.findById(id);
    }

    public Optional<Album> findAlbumByAlias(String albumAlias, String bandAlias) {
        return albumDao.findByAlias(albumAlias, bandAlias);
    }

    public Optional<Album> findAlbumById(Long id) {
        return albumDao.findById(id);
    }

    public List<Album> findAlbumsByBandId(Long bandId) {
        return albumDao.findAllByBandId(bandId);
    }

    public Optional<Track> findTrackById(Long id) {
        return trackDao.findById(id);
    }

    public List<Track> findTracksByAlbumId(Long albumId) {
        return trackDao.findAllByAlbumId(albumId);
    }

    public List<SearchResult<?>> search(String query) {
        List<SearchResult<?>> searchResults = new ArrayList<>();
        searchResults.addAll(bandDao.search(query, (band) -> {
            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .pathSegment(band.getLetter().toString())
                    .pathSegment(band.getAlias())
                    .build().toUriString();
        }));
        searchResults.addAll(albumDao.search(query, (album) -> {
            Band band = bandDao.findById(album.getBandId()).orElseThrow(() -> new ItemNotFoundException("band", album.getBandId()));
            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .pathSegment(band.getLetter().toString())
                    .pathSegment(band.getAlias())
                    .pathSegment(album.getAlias())
                    .build().toUriString();
        }));
        searchResults.addAll(trackDao.search(query, (track) -> {
            Album album = albumDao.findById(track.getAlbumId()).orElseThrow(() -> new ItemNotFoundException("album", track.getAlbumId()));
            Band band = bandDao.findById(album.getBandId()).orElseThrow(() -> new ItemNotFoundException("band", album.getBandId()));
            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .pathSegment(band.getLetter().toString())
                    .pathSegment(band.getAlias())
                    .pathSegment(album.getAlias())
                    .pathSegment(track.getAlias())
                    .build().toUriString();
        }));
        return searchResults;
    }
}
