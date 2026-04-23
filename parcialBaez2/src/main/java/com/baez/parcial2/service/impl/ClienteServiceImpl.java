package com.baez.parcial2.service.impl;

import com.baez.parcial2.dto.request.ClienteRequest;
import com.baez.parcial2.dto.request.ContactoRequest;
import com.baez.parcial2.dto.request.OportunidadRequest;
import com.baez.parcial2.dto.response.ClienteResponse;
import com.baez.parcial2.dto.response.ContactoResponse;
import com.baez.parcial2.dto.response.OportunidadResponse;
import com.baez.parcial2.entity.Cliente;
import com.baez.parcial2.entity.Contacto;
import com.baez.parcial2.entity.Oportunidad;
import com.baez.parcial2.entity.Usuario;
import com.baez.parcial2.exception.custom.DuplicateResourceException;
import com.baez.parcial2.exception.custom.ResourceNotFoundException;
import com.baez.parcial2.mapper.ClienteMapper;
import com.baez.parcial2.mapper.ContactoMapper;
import com.baez.parcial2.mapper.OportunidadMapper;
import com.baez.parcial2.repository.ClienteRepository;
import com.baez.parcial2.repository.ContactoRepository;
import com.baez.parcial2.repository.OportunidadRepository;
import com.baez.parcial2.repository.UsuarioRepository;
import com.baez.parcial2.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ContactoRepository contactoRepository;
    private final OportunidadRepository oportunidadRepository;
    private final UsuarioRepository usuarioRepository;
    private final ClienteMapper clienteMapper;
    private final ContactoMapper contactoMapper;
    private final OportunidadMapper oportunidadMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ContactoRepository contactoRepository,
                              OportunidadRepository oportunidadRepository, UsuarioRepository usuarioRepository,
                              ClienteMapper clienteMapper, ContactoMapper contactoMapper, OportunidadMapper oportunidadMapper) {
        this.clienteRepository = clienteRepository;
        this.contactoRepository = contactoRepository;
        this.oportunidadRepository = oportunidadRepository;
        this.usuarioRepository = usuarioRepository;
        this.clienteMapper = clienteMapper;
        this.contactoMapper = contactoMapper;
        this.oportunidadMapper = oportunidadMapper;
    }

    @Override
    @Transactional
    public ClienteResponse crearCliente(ClienteRequest request, String userEmail) {
        validarDuplicadosCreacion(request);
        Cliente cliente = clienteMapper.toEntity(request);
        cliente.setFechaCreacion(LocalDateTime.now());
        if (userEmail != null) {
            Usuario vendedor = usuarioRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario autenticado no encontrado"));
            cliente.setVendedor(vendedor);
        }
        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponse> listarClientes() {
        return clienteRepository.findAll().stream().map(clienteMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponse obtenerClientePorId(Long id) {
        return clienteMapper.toResponse(buscarCliente(id));
    }

    @Override
    @Transactional
    public ClienteResponse actualizarCliente(Long id, ClienteRequest request) {
        Cliente cliente = buscarCliente(id);
        if (clienteRepository.existsByNitAndIdNot(request.getNit(), id)) {
            throw new DuplicateResourceException("Ya existe otro cliente con el NIT " + request.getNit());
        }
        if (clienteRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new DuplicateResourceException("Ya existe otro cliente con el correo " + request.getEmail());
        }
        cliente.setNombre(request.getNombre());
        cliente.setNit(request.getNit());
        cliente.setEmail(request.getEmail());
        cliente.setTelefono(request.getTelefono());
        cliente.setDireccion(request.getDireccion());
        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Override
    @Transactional
    public void eliminarCliente(Long id) {
        Cliente cliente = buscarCliente(id);
        clienteRepository.delete(cliente);
    }

    @Override
    @Transactional
    public ContactoResponse asociarContacto(Long clienteId, ContactoRequest request) {
        Cliente cliente = buscarCliente(clienteId);
        Contacto contacto = contactoMapper.toEntity(request);
        contacto.setCliente(cliente);
        return contactoMapper.toResponse(contactoRepository.save(contacto));
    }

    @Override
    @Transactional
    public OportunidadResponse crearOportunidad(Long clienteId, OportunidadRequest request, String userEmail) {
        Cliente cliente = buscarCliente(clienteId);
        Oportunidad oportunidad = oportunidadMapper.toEntity(request);
        oportunidad.setCliente(cliente);
        if (userEmail != null) {
            Usuario responsable = usuarioRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuario autenticado no encontrado"));
            oportunidad.setResponsable(responsable);
        }
        return oportunidadMapper.toResponse(oportunidadRepository.save(oportunidad));
    }

    private Cliente buscarCliente(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente con id " + id + " no existe"));
    }

    private void validarDuplicadosCreacion(ClienteRequest request) {
        if (clienteRepository.existsByNit(request.getNit())) {
            throw new DuplicateResourceException("Ya existe un cliente con el NIT " + request.getNit());
        }
        if (clienteRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Ya existe un cliente con el correo " + request.getEmail());
        }
    }
}
