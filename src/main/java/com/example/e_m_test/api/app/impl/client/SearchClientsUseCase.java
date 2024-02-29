package com.example.e_m_test.api.app.impl.client;

import com.example.e_m_test.api.app.api.client.SearchClientsInBound;
import com.example.e_m_test.api.domain.client.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchClientsUseCase implements SearchClientsInBound {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> searchByFilters(String birth, String phone, String name, String email, int page, int size) {
        log.info("Searching by filters - birth: {}, phone: {}, name: {}, email: {}, page: {}, size: {}", birth, phone, name, email, page, size);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> cq = cb.createQuery(Client.class);
        Root<Client> client = cq.from(Client.class);
        List<Predicate> predicates = new ArrayList<>();

        if (birth != null) {
            predicates.add(cb.greaterThan(client.get("birth"), birth));
        }
        if (phone != null) {
            predicates.add(cb.equal(client.join("phones").get("number"), phone));
        }
        if (name != null) {
            predicates.add(cb.like(client.get("firstName"), "%" + name + "%"));
        }
        if (email != null) {
            predicates.add(cb.equal(client.join("emails").get("address"), email));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));
        TypedQuery<Client> query = entityManager.createQuery(cq);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);

        List<Client> results = query.getResultList();

        log.info("Found clients count: {}", results.size());

        return results;
    }
}
