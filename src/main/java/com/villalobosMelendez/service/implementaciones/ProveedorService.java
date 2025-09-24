package com.villalobosMelendez.service.implementaciones;

import com.villalobosMelendez.model.Proveedor;
import com.villalobosMelendez.repository.IGenericRepository;
import com.villalobosMelendez.repository.IProveedorRepository;
import com.villalobosMelendez.service.IProveedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProveedorService  extends GenericService<Proveedor, Integer> implements IProveedorService{
    private final IProveedorRepository repo;
    @Override
    protected IGenericRepository<Proveedor, Integer> getRepo() {
        return repo;
    }
}
