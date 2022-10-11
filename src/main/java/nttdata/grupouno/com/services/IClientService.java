package nttdata.grupouno.com.services;

import nttdata.grupouno.com.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IClientService {
    Mono<Client> created(Client client);
    Mono<Long> countByDocumentNumberAndDocumentTypeAndCellphone(String documentNumber, String documentType, Integer cellphone);
    Mono<Client> findById(String id);
    Flux<Client> findAll();
    Mono<Client> updateClient(Client client, String id);
    Mono<Void> deleteClient(String id);
}
