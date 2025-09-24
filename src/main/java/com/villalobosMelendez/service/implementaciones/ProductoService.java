package com.villalobosMelendez.service.implementaciones;

import com.villalobosMelendez.model.Producto;
import com.villalobosMelendez.repository.IGenericRepository;
import com.villalobosMelendez.repository.IProductoRepository;
import com.villalobosMelendez.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoService extends GenericService<Producto, Integer> implements IProductoService {
    private final IProductoRepository repo;
    @Override
    protected IGenericRepository<Producto, Integer> getRepo() {
        return repo;
    }
}
