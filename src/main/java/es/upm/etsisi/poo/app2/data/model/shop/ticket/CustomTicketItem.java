package es.upm.etsisi.poo.app2.data.model.shop.ticket;

import es.upm.etsisi.poo.app2.data.model.shop.products.BasicProduct;
import es.upm.etsisi.poo.app2.data.model.shop.products.CustomProduct;

public class CustomTicketItem extends BasicTicketItem {
    private final String[] texts;

    public CustomTicketItem(CustomProduct customProduct, Integer quantity, Double discountApplied, String[] texts ) {
        super(customProduct, quantity, discountApplied);
        this.texts = texts;
        for(int i = 0; i < texts.length; i++){
            texts[i].substring(3);
        }
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{class:ProductPersonalized, ");
        stringBuilder.append("id:").append(product.getId()).append(", ");
        stringBuilder.append("name:'").append(product.getName()).append("', ");
        stringBuilder.append("category:").append(((BasicProduct) product).getCategory()).append(", ");
        stringBuilder.append("price:").append(product.getPrice()).append(", ");
        stringBuilder.append("maxPersonal:").append(((CustomProduct) product).getNumberTexts()).append(", ");

        stringBuilder.append("personalizationList:[");
        for (int i = 0; i < texts.length; i++) {
            stringBuilder.append(texts[i]);
            if (i < texts.length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]}");

        return stringBuilder.toString();

    }

}
