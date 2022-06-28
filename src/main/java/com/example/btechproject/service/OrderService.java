package com.example.btechproject.service;


import com.example.btechproject.dto.cart.CartDto;
import com.example.btechproject.dto.cart.CartItemDto;
import com.example.btechproject.dto.checkout.CheckoutItemDto;
import com.example.btechproject.dto.order.PlaceOrderDto;
import com.example.btechproject.exceptions.OrderNotFoundException;
import com.example.btechproject.model.Order;
import com.example.btechproject.model.Orderitem;
import com.example.btechproject.model.User;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.example.btechproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartService cartService;

    @Autowired
    OrderItemsService orderItemsService;

    @Value("${BASE_URL}")
    private String baseURL;

    @Value("${STRIPE_SECRET_KEY}")
    private String apiKey;


    public Order saveOrder(PlaceOrderDto orderDto, User user, String sessionID) {
        Order order = getOrderFromDto(orderDto, user, sessionID);
        return orderRepository.save(order);
    }

    private Order getOrderFromDto(PlaceOrderDto orderDto, User user, String sessionID) {
        Order order = new Order(orderDto, user, sessionID);
        return order;
    }


    public List<Order> listOrders(User user) {
        List<Order> orderList = orderRepository.findAllByUserOrderByCreatedDateDesc(user);
        return orderList;
    }

    public Order getOrder(int orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            return order.get();
        }
        throw new OrderNotFoundException("Order not found");
    }


    @Transactional
    public void placeOrder(User user, String sessionId) {
        CartDto cartDto = cartService.listcartItems(user);

        PlaceOrderDto placeOrderDto = new PlaceOrderDto();
        placeOrderDto.setUser(user);
        placeOrderDto.setTotalPrice(cartDto.getTotalCost());

        Order newOrder = saveOrder(placeOrderDto, user, sessionId);
        List<CartItemDto> cartItemDtoList = cartDto.getCartItems();
        for (CartItemDto cartItemDto : cartItemDtoList) {
            Orderitem orderItem = new Orderitem(
                    newOrder,
                    cartItemDto.getProduct(),
                    cartItemDto.getQuantity(),
                    cartItemDto.getProduct().getPrice());
            orderItemsService.addOrderedProducts(orderItem);
        }
        cartService.deleteUserCartItems(user);
    }


    public Session createSession(List<CheckoutItemDto> checkoutItemDtoList) throws StripeException {
        String successURL = baseURL + "payment/success";
        String failedURL = baseURL + "payment/failed";

        Stripe.apiKey = apiKey;

        List<SessionCreateParams.LineItem> sessionItemsList = new ArrayList<SessionCreateParams.LineItem>();
        for (CheckoutItemDto checkoutItemDto : checkoutItemDtoList) {
            sessionItemsList.add(createSessionLineItem(checkoutItemDto));
        }

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failedURL)
                .addAllLineItem(sessionItemsList)
                .setSuccessUrl(successURL)
                .build();
        return Session.create(params);
    }


    SessionCreateParams.LineItem.PriceData createPriceData(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("INR")
                .setUnitAmount( ((long) checkoutItemDto.getPrice()) * 100)
                .setProductData(
                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                .setName(checkoutItemDto.getProductName())
                                .build())
                .build();
    }

    SessionCreateParams.LineItem createSessionLineItem(CheckoutItemDto checkoutItemDto) {
        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(checkoutItemDto))
                .setQuantity(Long.parseLong(String.valueOf(checkoutItemDto.getQuantity())))
                .build();
    }





    }

