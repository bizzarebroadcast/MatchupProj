package org.perscholas.casestudy.database.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.perscholas.casestudy.database.entity.gCharacter;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface CharacterDao extends JpaRepository<gCharacter, Long> {
    gCharacter findByName(String name);

    @Query("SELECT c FROM gCharacter c")
    List<gCharacter> findAllCharacters();
}