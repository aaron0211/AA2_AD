package com.aaron.AA2_AD.controller;

import com.aaron.AA2_AD.domain.Author;
import com.aaron.AA2_AD.domain.dto.AuthorDTO;
import com.aaron.AA2_AD.exception.AuthorNotFoundException;
import com.aaron.AA2_AD.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@Tag(name = "Authors", description = "Listado de autores")
public class AuthorController {

    private final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorService authorService;

    @Operation(summary = "Obtiene el listado de autores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Listado de autores",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = Author.class))))
    })
    @GetMapping(value = "/authors", produces = "application/json")
    public ResponseEntity<Set<Author>> getAuthors(){
        logger.info("inicio getAuthors");
        Set<Author> authors = authorService.findAll();
        logger.info("fin getAuthors");
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un autor por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Existe el autor",content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "404", description = "El autor no existe", content = @Content(schema = @Schema(implementation = Author.class)))
    })
    @GetMapping(value = "/authors/{id}", produces = "application/json")
    public ResponseEntity<Author> getAuthor(@PathVariable long id){
        Author author = authorService.findById(id)
                .orElseThrow(()->new AuthorNotFoundException(id));
        return  new ResponseEntity<>(author,HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el autor", content = @Content(schema = @Schema(implementation = Author.class)))
    })
    @PostMapping(value = "/authors", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author){
        Author addedauthor = authorService.addAuthor(author);
        logger.info("Añadido el autor: " + addedauthor.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(addedauthor);
    }

    //TODO modifyAuthor();

    @Operation(summary = "Elimina un autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Se ha eliminado el autor",content = @Content(schema = @Schema(implementation = Author.class))),
            @ApiResponse(responseCode = "404", description = "El autor no existe", content = @Content(schema = @Schema(implementation = Author.class)))
    })
    @DeleteMapping(value = "/authors/{id}",produces = "application/json")
    public ResponseEntity<Response> deleteAuthor(@PathVariable long id){
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(Response.noErrorResponse(),HttpStatus.OK);
    }
}
