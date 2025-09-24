package com.villalobosMelendez.controller;


import com.villalobosMelendez.dto.ProveedorDTO;
import com.villalobosMelendez.model.Proveedor;
import com.villalobosMelendez.service.IProveedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final IProveedorService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ProveedorDTO>>> findAll() throws Exception {
        List<ProveedorDTO> proveedores = service.findAll().stream()
                .map(this::convertToDto)
                .toList();

        // Crear EntityModel para cada proveedor con sus enlaces
        List<EntityModel<ProveedorDTO>> proveedoresWithLinks = proveedores.stream()
                .map(this::addLinksToProveedor)
                .toList();

        // Crear CollectionModel con enlaces de la colección
        CollectionModel<EntityModel<ProveedorDTO>> collectionModel = CollectionModel.of(proveedoresWithLinks);

        // Agregar enlaces a la colección
        collectionModel.add(Link.of("/proveedores", IanaLinkRelations.SELF));
        collectionModel.add(Link.of("/proveedores", "create"));

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProveedorDTO>> findById(@PathVariable("id") Integer id) throws Exception {
        ProveedorDTO proveedor = convertToDto(service.findById(id));
        EntityModel<ProveedorDTO> proveedorModel = addLinksToProveedor(proveedor);

        return ResponseEntity.ok(proveedorModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<ProveedorDTO>> save(@Valid @RequestBody ProveedorDTO dto) throws Exception {
        Proveedor obj = service.save(convertToEntity(dto));
        ProveedorDTO savedDto = convertToDto(obj);

        EntityModel<ProveedorDTO> proveedorModel = addLinksToProveedor(savedDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdProveedor())
                .toUri();

        return ResponseEntity.created(location).body(proveedorModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ProveedorDTO>> update(@Valid @RequestBody ProveedorDTO dto,
                                                            @PathVariable("id") Integer id) throws Exception {
        Proveedor obj = service.update(convertToEntity(dto), id);
        ProveedorDTO updatedDto = convertToDto(obj);
        EntityModel<ProveedorDTO> proveedorModel = addLinksToProveedor(updatedDto);

        return ResponseEntity.ok(proveedorModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);

        // Respuesta con enlaces después de eliminar
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Proveedor eliminado exitosamente");
        response.put("_links", Map.of(
                "proveedores", Map.of("href", "/proveedores"),
                "create", Map.of("href", "/proveedores")
        ));

        return ResponseEntity.ok(response);
    }

    // Método para agregar enlaces HATEOAS a un proveedor
    private EntityModel<ProveedorDTO> addLinksToProveedor(ProveedorDTO proveedor) {
        EntityModel<ProveedorDTO> proveedorModel = EntityModel.of(proveedor);

        // Enlaces básicos usando UriComponentsBuilder
        String baseUrl = "/proveedores";
        String selfUrl = baseUrl + "/" + proveedor.getIdProveedor();

        proveedorModel.add(Link.of(selfUrl, IanaLinkRelations.SELF));
        proveedorModel.add(Link.of(selfUrl, "update"));
        proveedorModel.add(Link.of(selfUrl, "delete"));
        proveedorModel.add(Link.of(baseUrl, "proveedores"));

        return proveedorModel;
    }

    // Métodos de conversión
    private ProveedorDTO convertToDto(Proveedor obj) {
        return modelMapper.map(obj, ProveedorDTO.class);
    }

    private Proveedor convertToEntity(ProveedorDTO dto) {
        return modelMapper.map(dto, Proveedor.class);
    }









   /* private final IProveedorService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> findAll() throws Exception{
        //ModelMapper modelMapper = new ModelMapper();
        //List<Proveedor> list = service.findAll();
        /*List<ProveedorDTO> list = service.findAll().stream().map(e -> new ProveedorDTO(
                e.getIdProveedor(),
                e.getNombre(),
                e.getRuc(),
                e.getDireccion(),
                e.getTelefono(),
                e.getCorreo()
        )).toList();*/
        /*List<ProveedorDTO> list = service.findAll().stream()
                .map(e -> modelMapper.map(e, ProveedorDTO.class))
                .toList();*/
   /**   List<ProveedorDTO> list = service.findAll().stream()
                .map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO>  findById(@PathVariable("id") Integer id) throws Exception{
        //Proveedor obj = service.findById(id);
        //ProveedorDTO obj = modelMapper.map(service.findById(id), ProveedorDTO.class);
        ProveedorDTO obj = convertToDto(service.findById(id));
        return  ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<ProveedorDTO>  save(@Valid @RequestBody ProveedorDTO dto) throws Exception{
        //Proveedor obj = service.save(proveedor);
        //Proveedor obj = service.save(modelMapper.map(dto, Proveedor.class));
        Proveedor obj = service.save(convertToEntity(dto));
        //return ResponseEntity.ok(obj);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdProveedor()).toUri();
        //return new ResponseEntity<>(obj, HttpStatus.CREATED);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> update(@Valid @RequestBody ProveedorDTO dto, @PathVariable("id") Integer id) throws Exception{
        //Proveedor obj = service.update(modelMapper.map(dto, Proveedor.class) ,id);
        Proveedor obj = service.update(convertToEntity(dto) ,id);
        return ResponseEntity.ok(modelMapper.map(obj, ProveedorDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return  ResponseEntity.noContent().build();
    }

    private ProveedorDTO convertToDto(Proveedor obj){
        return modelMapper.map(obj, ProveedorDTO.class);
    }
    private Proveedor convertToEntity(ProveedorDTO dto){
        return modelMapper.map(dto, Proveedor.class);

    }*/

}
