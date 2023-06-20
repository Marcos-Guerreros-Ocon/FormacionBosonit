package formacion.springbootreactive.person.repository;

import formacion.springbootreactive.person.domain.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PersonRepository extends ReactiveMongoRepository<Person,String> {
}
