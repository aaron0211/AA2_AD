package com.aaron.AA2_AD.controller;

import com.aaron.AA2_AD.domain.Member;
import com.aaron.AA2_AD.exception.MemberNotFoundException;
import com.aaron.AA2_AD.service.MemberService;
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
@Tag(name = "Members", description = "Listado de miembros")
public class MemberController {

    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    private MemberService memberService;

    @Operation(summary = "Obtiene el listado de socios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Listado de socios",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Member.class))))
    })
    @GetMapping(value = "/members", produces = "application/json")
    public ResponseEntity<Set<Member>> getMembers(){
        logger.info("inicio getMembers");
        Set<Member> members = memberService.findAll();
        logger.info("fin getMembers");
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un socio por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Existe el socio",content = @Content(schema = @Schema(implementation = Member.class))),
            @ApiResponse(responseCode = "404", description = "El socio no existe", content = @Content(schema = @Schema(implementation = Member.class)))
    })
    @GetMapping(value = "/members/{id}", produces = "application/json")
    public ResponseEntity<Member> getMember(@PathVariable long id){
        logger.info("Buscando socio: " + id);
        Member member = memberService.findById(id)
                .orElseThrow(()->new MemberNotFoundException(id));
        return new ResponseEntity<>(member,HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo socio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el socio", content = @Content(schema = @Schema(implementation = Member.class)))
    })
    @PostMapping(value = "/members", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Member> addMember(@RequestBody Member member){
        Member addedMember = memberService.addMember(member);
        logger.info("Añadido el socio: " + addedMember.getName() + " " + addedMember.getSurname());
        return ResponseEntity.status(HttpStatus.CREATED).body(addedMember);
    }

    @Operation(summary = "Modifica un socio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el socio",content = @Content(schema = @Schema(implementation = Member.class))),
            @ApiResponse(responseCode = "404", description = "El socio no existe", content = @Content(schema = @Schema(implementation = Member.class)))
    })
    @PutMapping(value = "/members/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Member> modifyMember(@PathVariable long id,@RequestBody Member newMember){
        Member member = memberService.modifyMember(id,newMember);
        logger.info("Modificado el socio: " + member.getId());
        return new ResponseEntity<>(member,HttpStatus.OK);
    }

    @Operation(summary = "Elimina un socio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Se ha eliminado el socio",content = @Content(schema = @Schema(implementation = Member.class))),
            @ApiResponse(responseCode = "404", description = "El socio no existe", content = @Content(schema = @Schema(implementation = Member.class)))
    })
    @DeleteMapping(value = "/members/{id}",produces = "application/json")
    public ResponseEntity<Response> deleteMember(@PathVariable long id){
        memberService.deleteMember(id);
        logger.info("Eliminado el socio: " + id);
        return new ResponseEntity<>(Response.noErrorResponse(),HttpStatus.OK);
    }

    @Operation(summary = "Modifica la direccion del socio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha modificado el campo",content = @Content(schema = @Schema(implementation = Member.class))),
            @ApiResponse(responseCode = "404", description = "El socio no existe", content = @Content(schema = @Schema(implementation = Member.class)))
    })
    @PatchMapping(value = "/members/{id}/change-address", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Member> changeAddress(@PathVariable long id, @RequestBody String address){
        Member member = memberService.findById(id)
                .orElseThrow(()->new MemberNotFoundException(id));
        member.setAddress(address);
        memberService.modifyMember(id,member);
        logger.info("Modificado la direccion a: " + address);
        return  new ResponseEntity<>(member,HttpStatus.OK);
    }

    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(MemberNotFoundException bnfe){
        Response response = Response.errorResponse(NOT_FOUND, bnfe.getMessage());
        logger.error(bnfe.getMessage(), bnfe);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
