package com.buyern.entityservice.dtos;

import com.buyern.entityservice.enums.BusinessType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class BusinessRegistrationDto {
    @NotEmpty(message = "business name can't be empty")
    private String name;
    @NotNull(message = "business type can't be empty")
    private BusinessType type;
    @NotNull(message = "logo not uploaded")
    private MultipartFile logo;
    //location
    @NotEmpty(message = "address can't be empty")
    private String address;
    @NotNull(message = "city can't be empty")
    private long city;
    @NotNull(message = "state can't be empty")
    private long state;
    @NotNull(message = "country can't be empty")
    private long country;
    @NotNull(message = "zipcode can't be empty")
    private long zipcode;
    //personal Details
    @NotEmpty(message = "registration id cant be empty can't be empty")
    private String govtRegistrationId;
    @NotEmpty(message = "about business can't be empty")
    private String shortAbout;
    @NotEmpty(message = "about business can't be empty")
    private String about;
    @NotNull(message = "date Established can't be empty")
    private String dateEstablished;

    //contact (how we contact the business)
    @NotEmpty(message = "email can't be empty")
    private String email;
    @NotEmpty(message = "phone can't be empty")
    private String phone;

    //Customer care: contact (how customers contact the business)
    @NotEmpty(message = "customer care email can't be empty")
    private String cCEmail;
    @NotEmpty(message = "customer care phone can't be empty")
    private String cCPhone;
}
