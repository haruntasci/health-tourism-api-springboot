package com.allianz.healthtourism.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import javax.swing.text.html.parser.Entity;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface IBaseRepository<Entity> extends JpaRepository<Entity, Long>, JpaSpecificationExecutor<Entity> {

    Optional<Entity> findByUuid(UUID uuid);
    int deleteByUuid(UUID uuid);

}
