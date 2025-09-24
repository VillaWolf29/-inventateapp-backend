package com.villalobosMelendez.service.implementaciones;
import com.villalobosMelendez.repository.IGenericRepository;
import com.villalobosMelendez.service.IGenericService;
import com.villalobosMelendez.exception.ModelNotFoundException;

import java.util.List;

public abstract class GenericService <T,ID> implements IGenericService<T,ID>{
    protected abstract IGenericRepository<T,ID> getRepo();
    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T update(T t, ID id) throws Exception {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("No existe el objeto "+ id));
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        //return getRepo().findById(id).orElse(null);
        return getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("No existe el objeto "+ id));
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("No existe el objeto "+ id));
        getRepo().deleteById(id);
    }

}
