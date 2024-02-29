package com.example.e_m_test.api.adapter.persistence.relational;

import com.example.e_m_test.api.domain.client.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientJpaRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("SELECT DISTINCT c FROM client c " +
            "LEFT JOIN c.phones p " +
            "LEFT JOIN c.emails e " +
            "WHERE (:birth IS NULL OR c.birth > :birth) " +
            "AND (:phone IS NULL OR p.number = :phone) " +
            "AND (:name IS NULL OR c.firstName LIKE CONCAT('%',:name,'%')) " +
            "AND (:email IS NULL OR e.address = :email)")
    Page<Client> findByFilters(
            @Param("birth") String birth,
            @Param("phone") String phone,
            @Param("name") String name,
            @Param("email") String email,
            Pageable pageable);
}
