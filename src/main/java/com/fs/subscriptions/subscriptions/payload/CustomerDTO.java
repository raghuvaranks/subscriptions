package com.fs.subscriptions.subscriptions.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private Long id;

    @NotBlank(message="First Name is mandatory")
    private String firstName;
    @NotBlank(message="Last Name is mandatory")
    private String lastName;
    private String company;
    @NotBlank(message="PhoneNumber is mandatory")
    @Size(min=10,max=10,message="PhoneNumber should be 10 digits")
    @Pattern(regexp = "^\\d{10}$",message = "Invalid Phone Number")
    private String phoneNumber;
    @Email(message="Email should be valid")
    private String email;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String zipCode;
    private String country;

}
