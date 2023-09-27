
public class OrderItem {
	
    private final Item item;
    private final String orderId;

    public OrderItem(Item item, String orderId) {
        this.item = item;
        this.orderId = orderId;
    }

    public Item getItem() {
        return item;
    }

    public String getOrderId() {
        return orderId;
    }
}

