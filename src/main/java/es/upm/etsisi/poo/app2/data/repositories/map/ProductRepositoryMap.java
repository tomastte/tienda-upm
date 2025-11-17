package es.upm.etsisi.poo.app2.data.repositories.map;

import es.upm.etsisi.poo.app2.data.model.shop.Product;
import es.upm.etsisi.poo.app2.data.repositories.ProductRepository;

public class ProductRepositoryMap extends RepositoryMapShop<Product> implements ProductRepository {

    public ProductRepositoryMap() {
        super();
    }

    @Override
    public void add(Product product) {

    }

    @Override
    public boolean find(Product product) {
        return true;
    }

}

