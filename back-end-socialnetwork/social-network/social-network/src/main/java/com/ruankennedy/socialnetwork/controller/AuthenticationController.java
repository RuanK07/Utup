package com.ruankennedy.socialnetwork.controller;


import com.ruankennedy.socialnetwork.config.exceptionConfig.standardError.commonStandardError.StandardError;
import com.ruankennedy.socialnetwork.config.securityConfig.Token;
import com.ruankennedy.socialnetwork.dto.request.Login;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "authentication", description = "Authenticate in the system")
public interface AuthenticationController {
    @Operation(summary = "Sign in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Token.class))}),
            @ApiResponse(responseCode = "400", description = "E-mail and / or password are / is wrong",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))})
    })
    ResponseEntity<Token> authenticate(@RequestBody @Valid Login loginData);

}