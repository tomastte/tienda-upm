package es.upm.etsisi.poo.app2.data.repositories;

import es.upm.etsisi.poo.app2.data.model.shop.Product;

public interface ProductRepository extends RepositoryShop<Product> {

    void add(Product product);

    boolean find(Product product);

}
