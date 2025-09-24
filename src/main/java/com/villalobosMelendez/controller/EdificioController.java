package com.villalobosMelendez.controller;

import com.villalobosMelendez.dto.EdificioDTO;
import com.villalobosMelendez.model.Edificio;
import com.villalobosMelendez.repository.IEdificioRepository;
import com.villalobosMelendez.service.IEdificioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/edificios")
@RequiredArgsConstructor
public class EdificioController {

    private final IEdificioService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<EdificioDTO>> findAll() throws Exception{
        ModelMapper modelMapper = new ModelMapper();
        List<EdificioDTO> list = service.findAll().stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EdificioDTO> findById(@PathVariable("id") Integer id) throws Exception{
        EdificioDTO obj = convertToDto(service.findById(id));
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<EdificioDTO> save(@Valid @RequestBody EdificioDTO dto) throws Exception{
        Edificio obj = service.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEdificio()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<EdificioDTO> update(@Valid @RequestBody EdificioDTO dto, @PathVariable("id") Integer id) throws Exception{
        Edificio obj = service.update(convertToEntity(dto),id);
        return ResponseEntity.ok(modelMapper.map(obj, EdificioDTO.class));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private EdificioDTO convertToDto(Edificio obj){
        return modelMapper.map(obj, EdificioDTO.class);
    }
    private Edificio convertToEntity(EdificioDTO dto){
        return modelMapper.map(dto, Edificio.class);
    }


}
