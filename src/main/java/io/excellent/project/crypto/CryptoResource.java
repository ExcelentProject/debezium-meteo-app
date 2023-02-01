package io.excellent.project.crypto;

import io.excellent.project.coincap.CryptoJsonService;
import io.quarkus.arc.DefaultBean;
import io.quarkus.scheduler.Scheduled;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Path("/crypto")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
@ApplicationScoped
public class CryptoResource {


    @RestClient
    private CryptoJsonService service;


    @Inject
    private CryptoService cryptoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response cryptoList() {
        log.debug("[API] returning crypto json list");
        return Response.ok(cryptoService.getAll()).build();
    }

    @Scheduled(every="{update.period}")
    void updateTable() {
        log.debug("[UPDATE] updating crypto table as scheduled");
        List<CryptoEntity> cryptoList = service.getAll().getData()
                .stream().map(CryptoEntity::new).collect(Collectors.toList());

        cryptoService.saveAll(cryptoList);
    }

}