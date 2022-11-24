package com.buyern.entityservice.controllers;

import com.buyern.entityservice.authorization.UserSession;
import com.buyern.entityservice.dtos.BusinessRegistrationDto;
import com.buyern.entityservice.dtos.ResponseDTO;
import com.buyern.entityservice.services.CompanyService;
import com.mashape.unirest.http.exceptions.UnirestException;
import filestorage.exceptions.EntityStorageException;
import filestorage.exceptions.FileUploadException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.Map;

@Slf4j
@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
public class BusinessController {
    private final CompanyService companyService;

    @PostMapping("business")
    public Map<String, Object> registerBusiness(@Valid @ModelAttribute BusinessRegistrationDto businessRegistrationDto, HttpServletRequest request, Principal principal) throws ParseException, IOException, FileUploadException, EntityStorageException, UnirestException {
        return companyService.registerBusiness(businessRegistrationDto, (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
    @DeleteMapping("business")
    public ResponseDTO<String> unregisterBusiness(@RequestParam String businessUid, HttpServletRequest request, Principal principal) throws ParseException, IOException, FileUploadException, EntityStorageException, UnirestException {
        return companyService.unregisterBusiness(businessUid);
    }
}
