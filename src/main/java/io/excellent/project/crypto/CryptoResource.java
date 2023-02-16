package io.excellent.project.crypto;

import io.excellent.project.coincap.CryptoJsonService;
import io.excellent.project.crypto.data.mysql.MysqlCryptoEntity;
import io.excellent.project.crypto.data.postgresql.MysqlCryptoService;
import io.excellent.project.crypto.data.postgresql.PostgresCryptoService;
import io.excellent.project.crypto.data.postgresql.PostgreslCryptoEntity;
import io.quarkus.scheduler.Scheduled;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    private PostgresCryptoService postgresCryptoService;

    @Inject
    private MysqlCryptoService mysqlCryptoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response cryptoList() {
        log.debug("[API] returning crypto json list");
        return Response.ok(postgresCryptoService.getAll()).build();
    }

    @GET
    @Path("/mysql")
    public Response cryptoListMysql() {
        log.debug("[API] returning crypto json list from mysql");
        return Response.ok(mysqlCryptoService.getAll()).build();
    }

    @Scheduled(every="{update.period}")
    void updateAll() {
        log.debug("[UPDATE] updating crypto table as scheduled");
        List<PostgreslCryptoEntity> cryptoList = service.getAll().getData()
                .stream().map(PostgreslCryptoEntity::new).collect(Collectors.toList());

        postgresCryptoService.saveAll(cryptoList);
        mysqlCryptoService.saveAll(cryptoList.stream().map(MysqlCryptoEntity::new).collect(Collectors.toList()));
    }

}