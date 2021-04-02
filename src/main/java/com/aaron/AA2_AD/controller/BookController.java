package com.aaron.AA2_AD.controller;

import com.aaron.AA2_AD.domain.Book;
import com.aaron.AA2_AD.exception.BookNotFoundException;
import com.aaron.AA2_AD.service.BookService;
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

import static com.aaron.AA2_AD.controller.Response.NOT_FOUND;

@RestController
@Tag(name = "Books", description = "Listado de libros")
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Operation(summary = "Obtiene el listado de libros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Listado de libros",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class))))
    })
    @GetMapping(value = "/books", produces = "application/json")
    public ResponseEntity<Set<Book>> getBooks(){
        logger.info("inicio getBooks");
        Set<Book> books = bookService.findAll();
        logger.info("fin getBooks");
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un libro por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Existe el libro",content = @Content(schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "404", description = "El libro no existe", content = @Content(schema = @Schema(implementation = Book.class)))
    })
    @GetMapping(value = "/books/{id}", produces = "application/json")
    public ResponseEntity<Book> getBook(@PathVariable long id){
        logger.info("Buscándo libro: " + id);
        Book book = bookService.findById(id)
                .orElseThrow(()->new BookNotFoundException(id));
        return  new ResponseEntity<>(book,HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el autor", content = @Content(schema = @Schema(implementation = Book.class)))
    })
    @PostMapping(value = "/books", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book addedBook = bookService.addBook(book);
        logger.info("Añadido el libro: " + addedBook.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(addedBook);
    }

    @Operation(summary = "Modifica un libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el libro",content = @Content(schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "404", description = "El libro no existe", content = @Content(schema = @Schema(implementation = Book.class)))
    })
    @PutMapping(value = "/books/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Book> modifyBook(@PathVariable long id,@RequestBody Book newBook){
        Book book = bookService.modifyBook(id,newBook);
        logger.info("Modificado el libro: " + book.getId());
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @Operation(summary = "Elimina un libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Se ha eliminado el libro",content = @Content(schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "404", description = "El libro no existe", content = @Content(schema = @Schema(implementation = Book.class)))
    })
    @DeleteMapping(value = "/books/{id}",produces = "application/json")
    public ResponseEntity<Response> deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
        logger.info("Eliminado el libro: " + id);
        return new ResponseEntity<>(Response.noErrorResponse(),HttpStatus.OK);
    }

    @Operation(summary = "Modifica el valor isGood de un libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha modificado el campo",content = @Content(schema = @Schema(implementation = Book.class))),
            @ApiResponse(responseCode = "404", description = "El libro no existe", content = @Content(schema = @Schema(implementation = Book.class)))
    })
    @PatchMapping(value = "/books/{id}/change-good", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Book> changeGood(@PathVariable long id, @RequestBody boolean good){
        Book book = bookService.findById(id)
                .orElseThrow(()->new BookNotFoundException(id));
        book.setIsGood(good);
        bookService.modifyBook(id,book);
        logger.info("Modificado el valor isGood a: " + good);
        return  new ResponseEntity<>(book,HttpStatus.OK);
    }

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(BookNotFoundException bnfe){
        Response response = Response.errorResponse(NOT_FOUND, bnfe.getMessage());
        logger.error(bnfe.getMessage(), bnfe);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
