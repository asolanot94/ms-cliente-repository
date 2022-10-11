package nttdata.grupouno.com.controller;

import nttdata.grupouno.com.model.Client;
import nttdata.grupouno.com.services.impl.ClientService;
import nttdata.grupouno.com.services.impl.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping
    public Mono<Map<String, Object>> createdClient(@RequestBody @Valid Mono<Client> client){
        Map<String, Object> response = new HashMap<>();
        return client.flatMap(a -> {
            return clientService.countByDocumentNumberAndDocumentTypeAndCellphone(a.getDocumentNumber(),a.getDocumentType(),a.getCellphone())
                    .flatMap(aLong -> {
                        if(aLong.intValue() != 0){
                            response.put("msg","El Cliente ya exite");
                            return Mono.just(response);
                        }
                        Mono<Client>  newClient=  clientService.created(a).doOnSuccess(o -> {
                            kafkaProducerService.sendMessage(o);
                        });
                        return newClient.flatMap(client1 -> {
                                    response.put("msg","El cliente fue creado con exito");
                                    response.put("obj",client1);
                                    return Mono.just(response);
                                });
                    });
        });
    }

    @GetMapping("/All")
    public Flux<Client> findAll(){
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Mono<Client>>> findById(@PathVariable("id") final String id){
        Mono<Client> mono = clientService.findById(id);
        return Mono.just(new ResponseEntity<>(mono, mono != null ? HttpStatus.OK : HttpStatus.NOT_FOUND));
    }
}
