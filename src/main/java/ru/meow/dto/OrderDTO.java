package ru.meow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ru.meow.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private long id;
    private List<ProductCountDTO> productList;
    private OrderStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;
}
