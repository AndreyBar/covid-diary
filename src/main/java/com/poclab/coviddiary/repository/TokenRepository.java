package com.poclab.coviddiary.repository;

import com.poclab.coviddiary.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {

  Optional<Token> findByUuidAndStatusTrue(UUID uuid);


}
