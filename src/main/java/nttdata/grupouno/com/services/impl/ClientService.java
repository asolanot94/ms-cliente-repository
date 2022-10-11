package nttdata.grupouno.com.services.impl;

import nttdata.grupouno.com.model.Client;
import nttdata.grupouno.com.repositories.IClientRepository;
import nttdata.grupouno.com.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ClientService implements IClientService {
    @Autowired
    IClientRepository clientRepository;

    @Override
    public Mono<Client> created(Client client) {
        client.setId(UUID.randomUUID().toString());
        return clientRepository.save(client);
    }

    @Override
    public Mono<Long> countByDocumentNumberAndDocumentTypeAndCellphone(String documentNumber, String documentType, Integer cellphone) {
        return clientRepository.findByDocumentNumberAndDocumentTypeAndCellphone(documentNumber,documentType,cellphone).count();
    }

    @Override
    public Mono<Client> findById(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public Flux<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Mono<Client> updateClient(Client client, String id) {
        return clientRepository.findById(id).flatMap(a ->
            clientRepository.save(client)
        ).switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Void> deleteClient(String id) {
        return clientRepository.deleteById(id);
    }
}
