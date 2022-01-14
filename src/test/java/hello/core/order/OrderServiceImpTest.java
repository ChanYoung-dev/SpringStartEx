package hello.core.order;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImpTest {

    @Test
    void createOrder() {
        OrderServiceImp orderServiceImp = new OrderServiceImp();
        orderServiceImp.createOrder(1L,"itemA", 10000);

    }

}