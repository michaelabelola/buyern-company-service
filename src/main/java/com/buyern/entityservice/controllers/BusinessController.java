package com.buyern.entityservice.controllers;

import com.buyern.entityservice.authorization.UserSession;
import com.buyern.entityservice.dtos.CompanyRegistrationDto;
import com.buyern.entityservice.models.Company;
import com.buyern.entityservice.services.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class BusinessController {
    private final CompanyService companyService;

    @PostMapping("company")
    public Company registerCompany(@Valid @ModelAttribute CompanyRegistrationDto companyRegistrationDto, HttpServletRequest request, Principal principal) {
//        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            log.info(userSession.toString());
//        log.info(principal.getName());
//        log.info(request.getRemoteUser());

        return companyService.registerCompany(companyRegistrationDto, (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
