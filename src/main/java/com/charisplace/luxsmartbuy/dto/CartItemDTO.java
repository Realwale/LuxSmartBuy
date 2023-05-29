package com.charisplace.luxsmartbuy.dto;

import com.charisplace.luxsmartbuy.model.Cart;
import com.charisplace.luxsmartbuy.model.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CartItemDTO {

    private Long id;

    @NotNull
    private Integer quantity;

    @NotNull
    private Product product;


    public CartItemDTO(Cart cart){
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }

}
