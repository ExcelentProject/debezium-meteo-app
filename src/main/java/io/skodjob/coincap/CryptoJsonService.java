package io.skodjob.coincap;

import io.skodjob.coincap.model.CryptoJsonPage;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient(baseUri = "https://api.coincap.io/")
@Path("/v2/")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public interface CryptoJsonService {

    @GET
    @Path("/assets")
    CryptoJsonPage getAll();
}
