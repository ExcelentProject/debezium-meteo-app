package io.excellent.project.coincap;

import io.excellent.project.coincap.model.CryptoJsonPage;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(baseUri = "https://api.coincap.io/")
@Path("/v2/")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public interface CryptoJsonService {

    @GET
    @Path("/assets")
    CryptoJsonPage getAll();
}
