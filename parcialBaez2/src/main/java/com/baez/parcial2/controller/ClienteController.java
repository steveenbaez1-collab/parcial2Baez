package com.baez.parcial2.controller;

import com.baez.parcial2.config.ApiResponseBuilder;
import com.baez.parcial2.dto.request.ClienteRequest;
import com.baez.parcial2.dto.request.ContactoRequest;
import com.baez.parcial2.dto.request.OportunidadRequest;
import com.baez.parcial2.dto.response.ApiResponse;
import com.baez.parcial2.dto.response.ClienteResponse;
import com.baez.parcial2.dto.response.ContactoResponse;
import com.baez.parcial2.dto.response.OportunidadResponse;
import com.baez.parcial2.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;
    private final ApiResponseBuilder responseBuilder;

    public ClienteController(ClienteService clienteService, ApiResponseBuilder responseBuilder) {
        this.clienteService = clienteService;
        this.responseBuilder = responseBuilder;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClienteResponse>> crearCliente(@Valid @RequestBody ClienteRequest request,
                                                                     Authentication authentication) {
        ClienteResponse response = clienteService.crearCliente(request, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseBuilder.success(HttpStatus.CREATED, "Cliente creado correctamente", response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClienteResponse>>> listarClientes() {
        return ResponseEntity.ok(responseBuilder.success(HttpStatus.OK, "Clientes listados correctamente", clienteService.listarClientes()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponse>> obtenerClientePorId(@PathVariable Long id) {
        return ResponseEntity.ok(responseBuilder.success(HttpStatus.OK, "Cliente encontrado", clienteService.obtenerClientePorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClienteResponse>> actualizarCliente(@PathVariable Long id,
                                                                          @Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.ok(responseBuilder.success(HttpStatus.OK, "Cliente actualizado correctamente", clienteService.actualizarCliente(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.ok(responseBuilder.success(HttpStatus.OK, "Cliente eliminado correctamente", null));
    }

    @PostMapping("/{clienteId}/contactos")
    public ResponseEntity<ApiResponse<ContactoResponse>> asociarContacto(@PathVariable Long clienteId,
                                                                         @Valid @RequestBody ContactoRequest request) {
        ContactoResponse response = clienteService.asociarContacto(clienteId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseBuilder.success(HttpStatus.CREATED, "Contacto asociado correctamente", response));
    }

    @PostMapping("/{clienteId}/oportunidades")
    public ResponseEntity<ApiResponse<OportunidadResponse>> crearOportunidad(@PathVariable Long clienteId,
                                                                             @Valid @RequestBody OportunidadRequest request,
                                                                             Authentication authentication) {
        OportunidadResponse response = clienteService.crearOportunidad(clienteId, request, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseBuilder.success(HttpStatus.CREATED, "Oportunidad creada correctamente", response));
    }
}
