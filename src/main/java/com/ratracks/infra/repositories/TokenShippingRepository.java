package com.ratracks.infra.repositories;

import com.ratracks.infra.entities.TokenShipping;
import com.ratracks.infra.schemas.TokenShippingSchema;

import java.util.Optional;
import java.util.UUID;

public interface TokenShippingRepository {
    void create(TokenShipping tokenShipping);

    void delete(UUID tokenShippingId);

    Optional<TokenShippingSchema> getTokenShippingByName(String name);

    void save(TokenShippingSchema tokenShipping);
}
