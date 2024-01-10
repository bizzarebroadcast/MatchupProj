package org.perscholas.casestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.perscholas.casestudy.database.entity.Matchup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.perscholas.casestudy.database.entity.gCharacter;
import java.util.List;
public interface MatchupDao extends JpaRepository<Matchup, Long> {
    public Matchup findByDescription(String Description);

    // Find all matchups involving a single character
    @Query("SELECT m FROM Matchup m WHERE m.char1 = :character OR m.char2 = :character")
    List<Matchup> findByCharacter(@Param("character") gCharacter character);

    // Find all matchups between two characters (order doesn't matter)
    @Query("SELECT m FROM Matchup m WHERE (m.char1 = :character1 AND m.char2 = :character2) OR " +
            "(m.char1 = :character2 AND m.char2 = :character1)")
    List<Matchup> findByCharacters(@Param("character1") gCharacter character1,
                                   @Param("character2") gCharacter character2);
    @Query("Select m FROM Matchup m")
    List<Matchup> getAllMatchups();
}