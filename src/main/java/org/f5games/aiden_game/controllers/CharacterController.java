package org.f5games.aiden_game.controllers;

import java.util.List;

import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.services.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "Characters API", description = "Operaciones para obtener la lista de characters o actualizar un character")
public class CharacterController {
    private CharacterService characterservice;

    public CharacterController (CharacterService characterService) {
        this.characterservice = characterService;
    }
    
    @Operation(
        summary = "Obtener todos los characters",
        description = "Este endpoint devuelve una lista de todos los characters guardados en la base de datos."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de characters obtenida correctamente",
        content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Character.class)))
    )
    @GetMapping(path = "/character")
    public List<Character> getCharacter() {
        return characterservice.getAll();
    }


    @Operation(
        summary = "Modificar un character",
        description = "Este endpoint actualiza los datos de un characters ya existente"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Character actualizado exitosamente",content = @Content(
            mediaType = "application/json", schema = @Schema(implementation = Character.class))),
        @ApiResponse(responseCode = "404", description = "Character no encontrado",content = @Content()),
        @ApiResponse(responseCode = "400", description = "Entrada de datos no válida",content = @Content())
    })
    @PutMapping(path = "/character")
    public ResponseEntity<Character> updateCharacter(
        @Parameter(description = "Body Json del character que se quiere actualizar", required = true)
        @RequestBody Character character) {
        return new ResponseEntity<>(characterservice.updateOne(character),HttpStatus.OK);
    }
}
