package formacion.block7crudvalidation.person.repository;

import formacion.block7crudvalidation.person.controller.dto.PersonOutputDto;
import formacion.block7crudvalidation.person.domain.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class PersonRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    private static final int PER_PAGE = 10;

    public List<Person> getPesonByCustomQuery(HashMap<String, Object> condictions) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Person> query = cb.createQuery(Person.class);
        Root<Person> root = query.from(Person.class);


        int page = (int) condictions.get("page");
        int firstResutl = (page - 1) * PER_PAGE;
        condictions.remove("page");

        Order order = cb.asc(root.get((String) condictions.get("order")));
        condictions.remove("order");

        String condition = (String) condictions.get("condition");
        condictions.remove("condition");

        List<Predicate> predicates = new ArrayList<>();
        if (condition.equals("asc")) {
            condictions.forEach((field, value) ->
            {
                switch (field) {
                    case "createdDate":
                        predicates.add(cb.greaterThan(root.<Date>get(field), (Date) value));
                        break;
                    default:
                        predicates.add(cb.greaterThan(root.get(field), value.toString()));
                }

            });
        }else{
            condictions.forEach((field, value) ->
            {
                switch (field) {
                    case "createdDate":
                        predicates.add(cb.lessThan(root.<Date>get(field), (Date) value));
                        break;
                    default:
                        predicates.add(cb.lessThan(root.get(field), value.toString()));
                }

            });
        }

        query.select(root).where(predicates.toArray(new Predicate[predicates.size()])).orderBy(order);
        return entityManager.createQuery(query).setFirstResult(firstResutl).setMaxResults(PER_PAGE).getResultList();
    }
}
