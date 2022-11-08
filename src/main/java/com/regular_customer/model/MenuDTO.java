package com.regular_customer.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MenuDTO {

    private Integer id;

    @NotNull
    @Size(max = 255)
    private String food;

    @NotNull
    private Integer price;

}
