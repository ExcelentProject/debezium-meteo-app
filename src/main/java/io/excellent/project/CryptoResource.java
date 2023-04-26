package io.excellent.project;

import io.excellent.project.coincap.CryptoJsonService;
import io.excellent.project.coincap.model.CryptoJsonPage;
import io.excellent.project.dmt.DmtJsonService;
import io.excellent.project.dmt.JsonSchema;
import io.excellent.project.dmt.JsonTranslator;
import io.quarkus.scheduler.Scheduled;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Path("/crypto")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
@ApplicationScoped
public class CryptoResource {

    private AtomicBoolean state = new AtomicBoolean(true);

    @RestClient
    private CryptoJsonService service;

    @RestClient
    private DmtJsonService dmtJsonService;

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
    void updateAll() {
        if (state.get()) {
            log.info("[UPDATE] updating crypto table as scheduled");
            List<String> databases = Arrays.asList("Mysql", "Postgresql", "Mongo");
            CryptoJsonPage page = service.getAll();
            JsonTranslator translator = new JsonTranslator();
            List<JsonSchema> rows = translator.translate(page, databases);

            for (JsonSchema jsonSchema : rows) {
                try (Response response = dmtJsonService.createAndUpsert(jsonSchema)
                        .await().atMost(Duration.ofSeconds(5))) {
                    //log.debug("Request completed successfully with status: " + response.getStatus());
                } catch (Exception e) {
                    log.error("Request to DMT failed. " + e.getMessage());
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}