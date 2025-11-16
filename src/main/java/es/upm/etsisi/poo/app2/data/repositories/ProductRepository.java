package es.upm.etsisi.poo.app2.data.repositories;

public interface ProductRepository<Product> extends Repository {

    void add(Product product);

    Product find(Product product);

}
