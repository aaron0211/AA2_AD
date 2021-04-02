package com.aaron.AA2_AD.controller;

import com.aaron.AA2_AD.domain.Loan;
import com.aaron.AA2_AD.exception.LoanNotFoundException;
import com.aaron.AA2_AD.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
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

import java.time.LocalDateTime;
import java.util.Set;

import static com.aaron.AA2_AD.controller.Response.NOT_FOUND;

@RestController
@Tag(name = "Loans", description = "Listado de prestamos")
public class LoanController {

    private final Logger logger = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    private LoanService loanService;

    @Operation(summary = "Obtiene el listado de prestamos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de prestamos", content = @Content(schema = @Schema(implementation = Loan.class)))
    })
    @GetMapping(value = "/loans", produces = "application/json")
    public ResponseEntity<Set<Loan>> getLoans(){
        logger.info("Obteniendo listado de prestamos");
        Set<Loan> loans = loanService.findAll();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un prestamo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el prestamo", content = @Content(schema = @Schema(implementation = Loan.class))),
            @ApiResponse(responseCode = "404", description = "No existe el prestamo", content = @Content(schema = @Schema(implementation = Loan.class)))
    })
    @GetMapping(value = "/loans/{id}", produces = "application/json")
    public ResponseEntity<Loan> getLoan(@PathVariable long id){
        logger.info("Obteniendo informacion del prestamo: " + id);
        Loan loan = loanService.findById(id)
                .orElseThrow(()->new LoanNotFoundException(id));
        return new ResponseEntity<>(loan,HttpStatus.OK);
    }

    @Operation(summary = "Registra un prestamo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Prestamos registrado", content = @Content(schema = @Schema(implementation = Loan.class)))
    })
    @PostMapping(value = "/loans", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Loan> addLoan(@RequestBody Loan loan){
        logger.info("Creado prestamo: " + loan.getId());
        Loan addedLoan = loanService.addLoan(loan);
        return new ResponseEntity<>(addedLoan,HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un prestamo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Prestamo modificado", content = @Content(schema = @Schema(implementation = Loan.class))),
            @ApiResponse(responseCode = "404", description = "El prestamo no existe", content = @Content(schema = @Schema(implementation = Loan.class)))
    })
    @PutMapping(value = "/loans/{id}", produces = "applitacion/json", consumes = "application/json")
    public ResponseEntity<Loan> modifyLoan(@PathVariable long id, @RequestBody Loan newLoan){
        logger.info("Modificado el prestamo: " + id);
        Loan loan = loanService.modifyLoan(id,newLoan);
        return new ResponseEntity<>(newLoan,HttpStatus.OK);
    }

    @Operation(summary = "Elimina un prestamo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha eliminado el prestamo", content = @Content(schema = @Schema(implementation = Loan.class))),
            @ApiResponse(responseCode = "404", description = "El prestamo no existe", content = @Content(schema = @Schema(implementation = Loan.class)))
    })
    @DeleteMapping(value = "/loans/{id}")
    public ResponseEntity<Response> deleteLoan(@PathVariable long id){
        logger.info("Eliminado el prestamo: " + id);
        loanService.deleteLoan(id);
        return new ResponseEntity<>(Response.noErrorResponse(),HttpStatus.OK);
    }

    @PatchMapping(value = "/loans/{id}/change-return", produces = "application/json")
    public ResponseEntity<Loan> changeReturn(@PathVariable long id, @RequestBody LocalDateTime returnDate){
        logger.info("Modificada la fecha de devolucion a: " + returnDate);
        Loan loan = loanService.findById(id)
                .orElseThrow(()->new LoanNotFoundException(id));
        loan.setReturnDate(returnDate);
        loanService.modifyLoan(id,loan);
        return new ResponseEntity<>(loan,HttpStatus.OK);
    }

    @ExceptionHandler(LoanNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(LoanNotFoundException lnfe){
        Response response = Response.errorResponse(NOT_FOUND, lnfe.getMessage());
        logger.error(lnfe.getMessage(), lnfe);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
