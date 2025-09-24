package com.villalobosMelendez.service.implementaciones;

import com.villalobosMelendez.model.Edificio;
import com.villalobosMelendez.repository.IGenericRepository;
import com.villalobosMelendez.repository.IEdificioRepository;
import com.villalobosMelendez.service.IEdificioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EdificioService  extends GenericService<Edificio, Integer> implements IEdificioService {
    private final IEdificioRepository repo;
    @Override
    protected IGenericRepository<Edificio, Integer> getRepo() {
        return repo;
    }
}
