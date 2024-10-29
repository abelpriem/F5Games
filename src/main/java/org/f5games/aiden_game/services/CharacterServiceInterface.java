package org.f5games.aiden_game.services;

import java.util.List;

import org.f5games.aiden_game.models.Character;

public interface CharacterServiceInterface {
    List<Character> getAll();

    Character updateOne(Character character);

    Character findById(Long id);
}
