package org.f5games.aiden_game.controllers;

import java.util.List;

import org.f5games.aiden_game.models.Backpack;
import org.f5games.aiden_game.services.BackpackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class BackpackController {

    private BackpackService services;

    public BackpackController(BackpackService services) {
        this.services = services;
    }

    // LISTAR OBJETOS DE LA MOCHILA
    @Operation(
        summary = "Obtener la lista de la mochila",
        description = "Este endpoint devuelve una lista de todos los objetos guardados en la mochila."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de la mochila obtenida correctamente",
        content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Backpack.class)))
    )
    @GetMapping(path = "/backpack")
    public ResponseEntity<List<Backpack>> retrieveObjects() {
        return new ResponseEntity<>(services.getAll(),HttpStatus.OK);
    }

    // AÑADIR OBJETO EN LA MOCHILA
    @Operation(
        summary = "Guardar un objeto en el Backpack",
        description = "Este endpoint crea un nuevo registro en el Backpack"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Backpack creado exitosamente",content = @Content(
            mediaType = "application/json", schema = @Schema(implementation = Backpack.class))),
        @ApiResponse(responseCode = "400", description = "Entrada de datos no válida",content = @Content())
    })
    @PostMapping(path = "/backpack")
    public ResponseEntity<Backpack> addObject(
        @Parameter(description = "ID del GameObject. Este parametro es una Foreign Key", required = true)
        @RequestParam Long objectId) {
        return new ResponseEntity<>(services.insertOne(objectId),HttpStatus.CREATED);
    }

    // MODIFICAR OBJETO DE LA MOCHILA
    @Operation(
        summary = "Modificar un objeto del Backpack",
        description = "Este endpoint actualiza los datos del objeto guardado en el Backpack por ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Backpack actualizado exitosamente",content = @Content(
            mediaType = "application/json", schema = @Schema(implementation = Backpack.class))),
        @ApiResponse(responseCode = "404", description = "Backpack ID no encontrado",content = @Content()),
        @ApiResponse(responseCode = "400", description = "Entrada de datos no válida",content = @Content())
    })
    @PutMapping(path = "/backpack/{id}")
    public ResponseEntity<Backpack> updateObject(
        @Parameter(description = "ID del Backpack", required = true)
        @PathVariable Long id,
        @Parameter(description = "ID del GameObject. Este parametro es una Foreign Key", required = true)
        @RequestParam Long objectId) {
        return new ResponseEntity<>(services.updateOne(id, objectId),HttpStatus.OK);
    }

    // ELIMINAR OBJETO DE LA MOCHILA
    @Operation(
        summary = "Eliminar un objeto del Backpack",
        description = "Este endpoint elimina los datos del objeto guardado en el Backpack por ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Backpack eliminado exitosamente",content = @Content()),
        @ApiResponse(responseCode = "404", description = "Backpack ID no encontrado",content = @Content()),
        @ApiResponse(responseCode = "400", description = "Entrada de datos no válida",content = @Content())
    })
    @DeleteMapping(path = "/backpack/{id}")
    public ResponseEntity<Void> deleteObject(
        @Parameter(description = "ID del Backpack", required = true)
        @PathVariable Long id) {
        services.deleteOne(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
