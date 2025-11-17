package es.upm.etsisi.poo.app2.services.shop;

import es.upm.etsisi.poo.app2.data.model.exceptions.InvalidAttributeException;
import es.upm.etsisi.poo.app2.data.model.shop.BasicProduct;
import es.upm.etsisi.poo.app2.data.model.shop.Category;
import es.upm.etsisi.poo.app2.data.model.shop.Product;
import es.upm.etsisi.poo.app2.data.repositories.ProductRepository;
import es.upm.etsisi.poo.app2.presentation.view.View;
import es.upm.etsisi.poo.app2.services.Service;
import es.upm.etsisi.poo.app2.services.exceptions.DuplicateException;
import es.upm.etsisi.poo.app2.services.exceptions.NotFoundException;

public class ProductService implements Service<Product> {

    private final ProductRepository productRepository;
    private final View view;

    public ProductService(ProductRepository productRepository, View view) {
        this.productRepository = productRepository;
        this.view = view;
    }

    @Override
    public void add(Product product, String id) {
        Integer idInteger= Integer.parseInt(id);
        if (this.productRepository.findById(idInteger) != null) {
            throw new DuplicateException("There is already a product with id " + id + " in the Catalog.");
        }
        this.productRepository.add(product, idInteger);
    }

    @Override
    public void remove(String id) {
        Integer idInteger= Integer.parseInt(id);
        if (this.productRepository.findById(idInteger) == null) {
            throw new NotFoundException("There is no product with id " + id + " in the Catalog.");
        }
        this.productRepository.remove(idInteger);
    }

    @Override
    public void list() {
        this.view.showList("Catalog:", this.productRepository.list());
    }

    public void add(Product product) {
        if (this.productRepository.find(product)) {
            throw new DuplicateException("There is already a product with this exact data in the Catalog.");
        }
        this.productRepository.add(product);
    }

    public void update(String id, String field, String value) {
        Integer idInteger= Integer.parseInt(id);
        Product prod = findProd(idInteger);
        switch (field.toUpperCase()) {
            case "NAME":
                prod.setName(value);
                break;
            case "CATEGORY":
                if (!(prod instanceof BasicProduct)) {
                    throw new InvalidAttributeException("Only BasicProduct or CustomProduct have category as a field");
                }
                ((BasicProduct) prod).setCategory(Category.valueOf(value.toUpperCase()));
                break;
            case "PRICE":
                prod.setPrice(Double.parseDouble(value));
                break;
            default:
                throw new InvalidAttributeException("Field not recognised");
                break;
        }
    }

    public Product findProd(Integer id) {
        Product prod = this.productRepository.findById(id);
        if (prod == null) {
            throw new NotFoundException("There is no product with id " + id + " in the Catalog.");
        }
        return prod;
    }

}
