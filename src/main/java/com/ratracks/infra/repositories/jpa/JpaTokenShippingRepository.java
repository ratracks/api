package com.ratracks.infra.repositories.jpa;

import com.ratracks.infra.entities.TokenShipping;
import com.ratracks.infra.schemas.TokenShippingSchema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaTokenShippingRepository extends JpaRepository<TokenShippingSchema, UUID> {
    TokenShippingSchema findTokenShippingSchemasByName(String name);
}

