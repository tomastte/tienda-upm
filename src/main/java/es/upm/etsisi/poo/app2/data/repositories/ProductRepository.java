package es.upm.etsisi.poo.app2.data.repositories;

import es.upm.etsisi.poo.app1.model.Product;

public interface ProductRepository extends Repository {

    void add(Product product);

    Product find(Product product);

}
