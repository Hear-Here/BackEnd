package ssuSoftware.hearHear.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssuSoftware.hearHear.entity.Music;

import java.util.Optional;

public interface MusicRepository extends JpaRepository <Music, Long>{
    public boolean existsByTitleAndArtist(String title, String artist);
    public Optional<Music> findByTitleAndArtist(String title, String artist);
}
