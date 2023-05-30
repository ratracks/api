package com.ratracks.infra.repositories;

import com.ratracks.infra.entities.TokenShipping;
import com.ratracks.infra.repositories.jpa.JpaTokenShippingRepository;
import com.ratracks.infra.schemas.TokenShippingSchema;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class TokenShippingRepositoryImpl implements TokenShippingRepository {

    private final JpaTokenShippingRepository repository;

    public TokenShippingRepositoryImpl(JpaTokenShippingRepository repository) {
        this.repository = repository;
    }


    @Override
    public void create(TokenShipping tokenShipping) {
        try {
            TokenShippingSchema tokenShippingSchema = new TokenShippingSchema(
                    tokenShipping.getId(),
                    tokenShipping.getCreatedAt(),
                    tokenShipping.getUpdatedAt(),
                    tokenShipping.getName(),
                    tokenShipping.getToken());
            repository.save(tokenShippingSchema);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<TokenShippingSchema> getTokenShippingByName(String name) {
        return Optional.ofNullable(repository.findTokenShippingSchemasByName(name));
    }

    @Override
    public void save(TokenShippingSchema tokenShipping) {
        repository.save(tokenShipping);
    }
}
