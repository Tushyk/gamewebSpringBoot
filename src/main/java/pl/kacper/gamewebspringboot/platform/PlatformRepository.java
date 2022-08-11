package pl.kacper.gamewebspringboot.platform;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    Platform findPlatformByName(String name);
}
