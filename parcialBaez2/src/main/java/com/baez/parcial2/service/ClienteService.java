package com.baez.parcial2.service;

import com.baez.parcial2.dto.request.ClienteRequest;
import com.baez.parcial2.dto.request.ContactoRequest;
import com.baez.parcial2.dto.request.OportunidadRequest;
import com.baez.parcial2.dto.response.ClienteResponse;
import com.baez.parcial2.dto.response.ContactoResponse;
import com.baez.parcial2.dto.response.OportunidadResponse;

import java.util.List;

public interface ClienteService {
    ClienteResponse crearCliente(ClienteRequest request, String userEmail);
    List<ClienteResponse> listarClientes();
    ClienteResponse obtenerClientePorId(Long id);
    ClienteResponse actualizarCliente(Long id, ClienteRequest request);
    void eliminarCliente(Long id);
    ContactoResponse asociarContacto(Long clienteId, ContactoRequest request);
    OportunidadResponse crearOportunidad(Long clienteId, OportunidadRequest request, String userEmail);
}
