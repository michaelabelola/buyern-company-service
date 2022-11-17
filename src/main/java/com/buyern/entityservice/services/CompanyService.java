package com.buyern.entityservice.services;

import com.buyern.entityservice.authorization.UserSession;
import com.buyern.entityservice.dtos.CompanyRegistrationDto;
import com.buyern.entityservice.models.Company;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Data
@Slf4j
@Service
public class CompanyService {
private final FileService fileService;
    public Company registerCompany(CompanyRegistrationDto companyRegistrationDto, UserSession userSession) {
        try {
            log.warn(String.valueOf(companyRegistrationDto.getLogo().getBytes().length));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.warn(companyRegistrationDto.getLogo().getOriginalFilename());


        return new Company();
    }
}
