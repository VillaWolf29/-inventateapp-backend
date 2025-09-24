package com.villalobosMelendez.controller;

import org.springframework.hateoas.*;
import lombok.RequiredArgsConstructor;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import com.villalobosMelendez.model.Producto;
import com.villalobosMelendez.dto.ProductoDTO;
import com.villalobosMelendez.service.IProductoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final IProductoService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ProductoDTO>>> findAll() throws Exception {
        List<ProductoDTO> productos = service.findAll().stream()
                .map(this::convertToDto)
                .toList();

        // Crear EntityModel para cada producto con sus enlaces
        List<EntityModel<ProductoDTO>> productosWithLinks = productos.stream()
                .map(this::addLinksToProducto)
                .toList();

        // Crear CollectionModel con enlaces de la colección
        CollectionModel<EntityModel<ProductoDTO>> collectionModel = CollectionModel.of(productosWithLinks);

        // Agregar enlaces a la colección
        collectionModel.add(linkTo(methodOn(ProductoController.class).findAll()).withSelfRel());
        collectionModel.add(linkTo(methodOn(ProductoController.class).save(null)).withRel("create"));

        return ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProductoDTO>> findById(@PathVariable("id") Integer id) throws Exception {
        ProductoDTO producto = convertToDto(service.findById(id));
        EntityModel<ProductoDTO> productoModel = addLinksToProducto(producto);

        return ResponseEntity.ok(productoModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<ProductoDTO>> save(@Valid @RequestBody ProductoDTO dto) throws Exception {
        Producto obj = service.save(convertToEntity(dto));
        ProductoDTO savedDto = convertToDto(obj);

        EntityModel<ProductoDTO> productoModel = addLinksToProducto(savedDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getIdProducto())
                .toUri();

        return ResponseEntity.created(location).body(productoModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<ProductoDTO>> update(@Valid @RequestBody ProductoDTO dto,
                                                           @PathVariable("id") Integer id) throws Exception {
        Producto obj = service.update(convertToEntity(dto), id);
        ProductoDTO updatedDto = convertToDto(obj);
        EntityModel<ProductoDTO> productoModel = addLinksToProducto(updatedDto);

        return ResponseEntity.ok(productoModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);

        // Respuesta con enlaces después de eliminar
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Producto eliminado exitosamente");
        response.put("_links", Map.of(
                "productos", Map.of("href", linkTo(ProductoController.class).toString()),
                "create", Map.of("href", linkTo(methodOn(ProductoController.class).save(null)).toString())
        ));

        return ResponseEntity.ok(response);
    }

    // Método para agregar enlaces HATEOAS a un producto
    private EntityModel<ProductoDTO> addLinksToProducto(ProductoDTO producto) {
        EntityModel<ProductoDTO> productoModel = EntityModel.of(producto);

        // Enlaces básicos usando UriComponentsBuilder
        String baseUrl = "/productos";
        String selfUrl = baseUrl + "/" + producto.getIdProducto();

        productoModel.add(Link.of(selfUrl, IanaLinkRelations.SELF));
        productoModel.add(Link.of(selfUrl, "update"));
        productoModel.add(Link.of(selfUrl, "delete"));
        productoModel.add(Link.of(baseUrl, "productos"));

        return productoModel;
    }

    private ProductoDTO convertToDto(Producto obj) {
        return modelMapper.map(obj, ProductoDTO.class);
    }

    private Producto convertToEntity(ProductoDTO dto) {
        return modelMapper.map(dto, Producto.class);
    }

    

}
