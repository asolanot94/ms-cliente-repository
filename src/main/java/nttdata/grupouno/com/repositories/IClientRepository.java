package nttdata.grupouno.com.repositories;

import nttdata.grupouno.com.model.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IClientRepository extends ReactiveMongoRepository<Client, String> {
    Flux<Client> findByDocumentNumberAndDocumentTypeAndCellphone(String documentNumber, String documentType, Integer cellphone);
}
