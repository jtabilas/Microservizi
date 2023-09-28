package com.example.cartserver;

import com.example.cartserver.controller.CartController;
import com.example.cartserver.model.Cart;
import com.example.cartserver.service.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
@ExtendWith(MockitoExtension.class)
public class CartControllerUnitTest {

    @MockBean
    private CartService cartService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_create_cart() throws  Exception {
        // given
        Cart cart = new Cart();
        given(cartService.createCart()).willReturn(cart);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/cart"));

        // then
        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.idCart").exists())
                .andDo(print());
    }

    @Test
    public void test_addItem_onCart_notExits() throws Exception {
        // given
        int cartId = 1;
        int productId = 2;
        int quantity = 10;
        willDoNothing().given(cartService).addToCart(cartId,productId,quantity);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .post("/addCart/{cartId}/{productId}/{quantity}", cartId,productId,quantity));

        // then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void test_listCart() throws  Exception {
        // given
        List<Cart> cart = new ArrayList<>();
        given(cartService.getCart()).willReturn(cart);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/cart"));

        // then
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void test_deleteItem_butNotExists() throws  Exception {

        // given
        int cartId = 1;
        int cartItemId = 2;
        String a = "test";
        given(cartService.deleteCartItem(cartId,cartItemId)).willReturn(a);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .delete("/deleteItem/{idCart}/{idItemCart}", cartId, cartItemId));

        // then
        response.andExpect((status().isNotFound()))
                .andDo(print());

    }

}
