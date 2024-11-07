package org.f5games.aiden_game.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharacterTest {

    private Character character;

    @BeforeEach
    public void setUp() {
        character = Character.builder()
                .id(1L)
                .name("Aiden")
                .strength(15)
                .health(100)
                .build();
    }

    @Test
    void testCharacterHas4Attributes() {
        Field[] characterFields = character.getClass().getDeclaredFields();
        assertThat(characterFields.length, is(4));
    }
    
    
    @Test
    void testCharacterProperties() {
        assertThat(character.getId(), is(1L));
        assertThat(character.getName(), is(notNullValue()));
        assertThat(character.getName(), is("Aiden"));
        assertThat(character.getStrength(), is(greaterThan(0)));
        assertThat(character.getStrength(), is(15));
        assertThat(character.getHealth(), is(both(greaterThan(0)).and(lessThanOrEqualTo(100))));
        assertThat(character.getHealth(), is(100));

    }
    
  
}




