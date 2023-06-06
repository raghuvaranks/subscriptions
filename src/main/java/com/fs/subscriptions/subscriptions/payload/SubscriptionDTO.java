package com.fs.subscriptions.subscriptions.payload;

import com.fs.subscriptions.subscriptions.entity.Profiles;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDTO {

    private Long id;
    @NotBlank(message="Subscription name is mandatory")
    private String name;
    private Double amount;
    private Date dueDate;
    private Set<Profiles> profiles;
    private String customerId;

}
