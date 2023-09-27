import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Errors.DuplicateIDException;
import Errors.NotVIPException;

class Order {
    private static final Set<String> orderIds = new HashSet<>();
    private final String id;
    private final String name;
    private final String deliveryAddress;
    private final List<OrderItem> items = new ArrayList<>();
    private final Customer customer;
    private double totalPrice;
    private final PaymentType paymentType;
    private final Date orderDate;

    public Order(String id, String name, String deliveryAddress, List<Item> items, Customer customer, PaymentType paymentType, Date orderDate) throws NotVIPException, DuplicateIDException {
        if (!orderIds.add(id)) {
            throw new DuplicateIDException("Order ID already exists: " + id);
        }
        this.id = id;
        this.name = name;
        this.deliveryAddress = deliveryAddress;
        this.customer = customer;
        this.paymentType = paymentType;
        this.orderDate = orderDate;

        for (Item item : items) {
            addOrderItem(new OrderItem(item, id));
            customer.addFavoriteItem(item); 
        }
        calculateTotalPrice();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    private void addOrderItem(OrderItem orderItem) throws DuplicateIDException {
        for (OrderItem item : items) {
            if (item.getOrderId().equals(orderItem.getOrderId())) {
                throw new DuplicateIDException("Item with the same ID already exists in the order.");
            }
        }
        items.add(orderItem);
    }

    private void calculateTotalPrice() throws NotVIPException {
        for (OrderItem orderItem : items) {
            totalPrice += orderItem.getItem().getPrice();
        }

        if (customer.getType() == CustomerType.VIP) {
            if (customer.getDiscount() != null) {
                totalPrice -= totalPrice * customer.getDiscount();
            } else {
                throw new NotVIPException("Not a VIP customer");
            }
        }
    }
}
