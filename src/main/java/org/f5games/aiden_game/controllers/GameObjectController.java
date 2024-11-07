package org.f5games.aiden_game.controllers;

import java.util.List;

import org.f5games.aiden_game.models.GameObject;
import org.f5games.aiden_game.services.GameObjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class GameObjectController {

    private final GameObjectService gameObjectService;

    public GameObjectController(GameObjectService gameObjectService) {
        this.gameObjectService = gameObjectService;
    }

    @Operation(summary = "Obtener la lista de todos los GameObject", description = "Este endpoint devuelve una lista de todos los GameObject guardados en la base de datos.")

    @ApiResponse(responseCode = "200", description = "Lista de GameObject obtenida correctamente", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GameObject.class))))

    @GetMapping(path = "/objects")
    public List<GameObject> getObjects() {
        return gameObjectService.getAll();
    }
}