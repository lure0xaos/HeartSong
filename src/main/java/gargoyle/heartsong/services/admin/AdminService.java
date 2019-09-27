package gargoyle.heartsong.services.admin;

import gargoyle.heartsong.dao.app.AlbumDao;
import gargoyle.heartsong.dao.app.BandDao;
import gargoyle.heartsong.dao.app.TrackDao;
import gargoyle.heartsong.dao.user.SessionDao;
import gargoyle.heartsong.dao.user.UserDao;
import gargoyle.heartsong.err.ItemNotFoundException;
import gargoyle.heartsong.model.app.Album;
import gargoyle.heartsong.model.app.Band;
import gargoyle.heartsong.model.app.SearchResult;
import gargoyle.heartsong.model.app.Track;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final BandDao bandDao;
    private final AlbumDao albumDao;
    private final TrackDao trackDao;
    private final UserDao userDao;
    private final SessionDao sessionDao;

    public Optional<Band> findBandById(Long id) {
        return bandDao.findById(id);
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

    public List<Band> getBands() {
        return bandDao.findAll();
    }

    public void saveBand(Band band) {
        bandDao.save(band);
    }

    public void deleteBand(Long bandId) {
        findAlbumsByBandId(bandId).stream().map(Album::getId).forEach(this::deleteAlbum);
        bandDao.delete(bandId);
    }

    public void saveAlbum(Album album) {
        albumDao.save(album);
    }

    public void deleteAlbum(Long albumId) {
        findTracksByAlbumId(albumId).stream().map(Track::getId).forEach(this::deleteTrack);
        albumDao.delete(albumId);
    }

    public void saveTrack(Track track) {
        trackDao.save(track);
    }

    public void deleteTrack(Long trackId) {
        trackDao.delete(trackId);
    }

    public List<UserDetails> getUsers() {
        return userDao.findAll();
    }

    public Optional<UserDetails> findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public void saveUser(UserDetails user) {
        userDao.save(user);
    }

    public void deleteUser(String username) {
        userDao.delete(username);
    }

    public List<Session> findSessionsByUsername(String username) {
        return sessionDao.findByPrincipalName(username);
    }

    public UserDetails createUser() {
        return userDao.create();
    }

    public void kickUser(String username) {
        sessionDao.kick(username);
    }
}
