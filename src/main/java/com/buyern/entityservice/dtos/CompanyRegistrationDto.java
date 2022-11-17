package com.buyern.entityservice.dtos;

import com.buyern.entityservice.enums.CompanyType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Data
public class CompanyRegistrationDto {
    @NotEmpty(message = "company name can't be empty")
    private String name;
    @NotNull(message = "company type can't be empty")
    private CompanyType type;
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
    @NotEmpty(message = "about company can't be empty")
    private String shortAbout;
    @NotEmpty(message = "about company can't be empty")
    private String about;
    @NotNull(message = "date Established can't be empty")
    private String dateEstablished;

    //contact (how we contact the company)
    @NotEmpty(message = "email can't be empty")
    private String email;
    @NotEmpty(message = "phone can't be empty")
    private String phone;

    //Customer care: contact (how customers contact the company)
    @NotEmpty(message = "customer care email can't be empty")
    private String cCEmail;
    @NotEmpty(message = "customer care phone can't be empty")
    private String cCPhone;
}
