import java.util.HashSet;
import java.util.Set;

import Errors.DuplicateIDException;

public class Customer {
    private static final Set<String> customerIds = new HashSet<>();
    private final String firstName;
    private final String lastName;
    private final String id;
    private final String email;
    private final String deliveryAddress;
    private final CustomerType type;
    private Double discount;
    private final Set<Item> favoriteItems = new HashSet<>();
    private Gift gift;

    public Customer(String id, String firstName, String lastName, String email, String deliveryAddress, CustomerType type) throws DuplicateIDException {
        if (!customerIds.add(id)) {
            throw new DuplicateIDException("Customer ID already exists: " + id);
        }
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.deliveryAddress = deliveryAddress;
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public CustomerType getType() {
        return type;
    }

    public Double getDiscount() {
        return discount;
    }

    public Set<Item> getFavoriteItems() {
        return favoriteItems;
    }

    public Gift getGift() {
        return gift;
    }

    public void addFavoriteItem(Item item) {
        favoriteItems.add(item);
    }

    public void removeFavoriteItem(Item item) {
        favoriteItems.remove(item);
    }

    public void takeGift(Gift gift) {
        this.gift = gift;
    }

    public void openGift() {
        if (gift != null) {
            gift.open();
        }
    }
}
