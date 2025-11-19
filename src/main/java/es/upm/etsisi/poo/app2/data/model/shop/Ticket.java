package es.upm.etsisi.poo.app2.data.model.shop;

import es.upm.etsisi.poo.app2.data.model.Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Ticket extends Entity<String> {
    private final List<TicketItem> itemList;
    private final Integer MAX_PRODUCTS = 100;
    private Integer numberOfProducts;
    private final String clientId;
    private final String cashierId;
    private Status status;

    private final LocalDateTime openDate;
    private LocalDateTime closeDate;

    public Ticket(String id, String clientId, String cashierId){
        super(id);
        this.clientId = clientId;
        this.cashierId = cashierId;
        this.itemList = new LinkedList<TicketItem>();
        this.numberOfProducts = 0;
        this.status = Status.EMPTY;
        this.openDate = LocalDateTime.now();
    }

    public Ticket(String clientId, String cashierId){
        this(generateId(), clientId, cashierId);
    }

    private static String generateId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm");
        String timestamp = LocalDateTime.now().format(formatter);
        int randomNum = new Random().nextInt(90000) + 10000; // 5 digit random
        return timestamp + "-" + randomNum;
    }

    public List<TicketItem> getItemList(){
        return Collections.unmodifiableList(this.itemList);
    }

    public Integer getNumberOfProducts(){
        return this.numberOfProducts;
    }

    public String getClientId(){
        return this.clientId;
    }

    public String getCashierId(){
        return this.cashierId;
    }

    public Status getStatus(){
        return this.status;
    }

    public void add(Product product, Integer quantity){
        boolean itemFound = false;
        Iterator<TicketItem> iterator = this.itemList.iterator();
        while (iterator.hasNext() && !itemFound) {
            TicketItem item = iterator.next();
            if (item.getProduct().equals(product)) {
                itemFound = true;
                item.setQuantity(item.getQuantity() + quantity);
            }
        }

        if (!itemFound) {
            double discountApplied = 0.0;
            if (product instanceof BasicProduct) {
                discountApplied = ((BasicProduct) product).getCategory().getDiscount();
            }
            TicketItem newItem = new TicketItem(product, quantity);
            this.itemList.add(newItem);
        }

        this.numberOfProducts += quantity;
        this.itemList.sort(null);

        if (status == Status.EMPTY) {
            status = Status.ACTIVE;
        }
    }

    public void remove(Integer productId){
        boolean itemFound = false;
        Iterator<TicketItem> iterator = this.itemList.iterator();
        while (iterator.hasNext() && !itemFound) {
            TicketItem item = iterator.next();
            if (item.getProduct().getId() == productId) {
                itemFound = true;
                this.numberOfProducts -= item.getQuantity();
                iterator.remove();
            }
        }

        if (itemList.isEmpty()) {
            status = Status.EMPTY;
        }
    }

    public void closeTicket(){
        if(this.status == Status.CLOSED){
            return;
        }
        this.status = Status.CLOSED;
        this.closeDate = LocalDateTime.now();

        if (!this.getId().contains("-")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm");
            String closeTimestamp = closeDate.format(formatter);
        }
    }

    private double calculateTotalDiscount(){
        double result = 0.0;

        Map<Category, Integer> quantitiesEachCategory = new HashMap<>();
        for (TicketItem item : itemList) {
            if (item.getProduct() instanceof BasicProduct) {
                Category category = ((BasicProduct) item.getProduct()).getCategory();
                int currentQuantity = quantitiesEachCategory.getOrDefault(category, 0);
                quantitiesEachCategory.put(category, currentQuantity + item.getQuantity());
            }
        }

        for (TicketItem item : itemList) {
            if (item.getProduct() instanceof BasicProduct) {
                Category category = ((BasicProduct) item.getProduct()).getCategory();
                int totalEachCategory = quantitiesEachCategory.get(category);
                if (totalEachCategory > 1) {
                    result += item.getDiscount();
                }
            }
        }

        return result;
    }

    private double calculateTotalPrice() {
        double result = 0.0;
        for (TicketItem item : this.itemList) {
            result += item.getSubtotal();
        }
        return result;
    }

    private double calculateFinalPrice() {
        return this.calculateTotalPrice() - this.calculateTotalDiscount();
    }
}
