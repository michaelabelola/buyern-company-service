package com.buyern.entityservice.services;

import com.buyern.entityservice.authorization.UserSession;
import com.buyern.entityservice.dtos.BusinessRegistrationDto;
import com.buyern.entityservice.dtos.ResponseDTO;
import com.buyern.entityservice.enums.BusinessState;
import com.buyern.entityservice.exceptions.RecordNotFoundException;
import com.buyern.entityservice.models.Business;
import com.buyern.entityservice.models.BusinessDetail;
import com.buyern.entityservice.models.location.City;
import com.buyern.entityservice.models.location.Country;
import com.buyern.entityservice.models.location.Location;
import com.buyern.entityservice.models.location.State;
import com.buyern.entityservice.repositories.BusinessRepository;
import com.mashape.unirest.http.exceptions.UnirestException;
import filestorage.EntityFileUploader;
import filestorage.EntityStorage;
import filestorage.enums.Container;
import filestorage.exceptions.EntityStorageException;
import filestorage.exceptions.FileUploadException;
import filestorage.models.EntityFile;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Data
@Slf4j
@Service
public class CompanyService {
    private final BusinessRepository businessRepository;

    /**
     * <h3><b>STEPS</b></h3>
     * register business
     * create storage account
     * upload logo
     * register finance
     * register inventory
     */
    @Transactional
    public Map<String, Object> registerBusiness(BusinessRegistrationDto businessRegistrationDto, UserSession userSession) throws ParseException, IOException, FileUploadException, EntityStorageException, UnirestException {

        String fileExt = Objects.requireNonNull(businessRegistrationDto.getLogo().getContentType()).split("/")[1];
        // TODO: check if file is an image
        Business business = new Business();
        BusinessDetail detail = new BusinessDetail();
        detail.setAbout(businessRegistrationDto.getAbout());
        detail.setEmail(businessRegistrationDto.getEmail());
        detail.setDateEstablished(new SimpleDateFormat("yyyy-MM-dd").parse(businessRegistrationDto.getDateEstablished().strip()));
        detail.setGovtRegistrationId(businessRegistrationDto.getGovtRegistrationId());
        detail.setPhone(businessRegistrationDto.getPhone());
        detail.setRegistererUid(userSession.getUid());
        business.setDetail(detail);
        Location location = new Location();
        location.setAddress(businessRegistrationDto.getAddress());
        location.setState(new State(businessRegistrationDto.getState()));
        location.setCity(new City(businessRegistrationDto.getCity()));
        location.setCountry(new Country(businessRegistrationDto.getCountry()));
        location.setZipcode(businessRegistrationDto.getZipcode());
        business.setLocation(location);
        business.setShortAbout(business.getShortAbout());
        business.setName(businessRegistrationDto.getName());
        business.setState(BusinessState.INACTIVE);
        business.setType(businessRegistrationDto.getType());
        business.setUid(UUID.randomUUID().toString());
        Business savedBusiness = businessRepository.saveAndFlush(business);
        //create entity storage account
        try {
            EntityStorage.Register(savedBusiness.getUid());
        } catch (EntityStorageException e) {
            throw new EntityStorageException("Error creating storage account for business");
        }
        //upload entity logo
        File logo = File.createTempFile(savedBusiness.getUid(), "-logo");
        businessRegistrationDto.getLogo().transferTo(logo);
        String fileName = "logo." + fileExt;
        Map<String, String> image = new EntityFileUploader(EntityFile.builder().entityUid(savedBusiness.getUid()).container(Container.PUBLIC).name(fileName).file(logo).build()).upload();
        savedBusiness.setLogo(image.get(fileName));

        switch (savedBusiness.getType()) {
            case E_COMMERCE -> registerEcommerce(savedBusiness);
            case LOGISTICS -> registerLogistics(savedBusiness);
        }


        return Map.of("business", savedBusiness, "JWT", "brfqb873o347g8ot7q38fg348");
    }

    private void registerLogistics(Business business) {

    }

    private void registerEcommerce(Business business) {
        /*
         * TODO: finances, inventories, stakeholderManager, location service, order service,
         *  */
    }

    public ResponseDTO<String> unregisterBusiness(String businessUid) {
        if (businessRepository.deleteByUid(businessUid) >= 1)
            return new ResponseDTO.OfString("00", "DELETED", "business unregistered");
        throw new RecordNotFoundException("Entity not found");
    }
}
