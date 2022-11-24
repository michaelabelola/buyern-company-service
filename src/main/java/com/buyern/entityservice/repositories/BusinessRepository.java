package com.buyern.entityservice.repositories;

import com.buyern.entityservice.models.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {
    long deleteByUid(String uid);
}