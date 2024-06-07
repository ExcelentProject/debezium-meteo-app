package io.skodjob.coincap;

import io.skodjob.coincap.model.CryptoJsonPage;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

@Path("/crypto")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
@ApplicationScoped
public class CryptoResource {

    private AtomicBoolean state = new AtomicBoolean(true);

    @ConfigProperty(name = "table", defaultValue = "coincap")
    String table;

    @RestClient
    private CryptoJsonService service;

    @Inject
    CoinCapRepository coinCapRepository;

    @GET
    public Response base() {
        return Response.ok().build();
    }

    @GET
    @Path("/test")
    public Response testRest() {
        return Response.ok().build();
    }

    @GET
    @Path("/switch")
    public Response switchState() {
        boolean cState = state.get();
        state.getAndSet(!cState);
        log.info("Setting application state to: " + !cState);
        return Response.ok(state.get()).build();
    }

    @Scheduled(every="{update.period}")
    @Transactional
    void updateAll() throws SQLException {
        if (state.get()) {
            log.info("[UPDATE] updating crypto table as scheduled");
            CryptoJsonPage page = service.getAll();
            coinCapRepository.upsertAll(page.getData());
        }
    }
}