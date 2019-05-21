package pl.pociecha.repositories;

import java.util.List;

public interface GenericRepository <O, V> {

    void save(O o);

    O get (V v);

    O update (O o);

    void delete (O o);

    List<O> getAll();

}
