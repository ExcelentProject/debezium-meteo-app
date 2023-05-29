package io.excellent.project.dmt;

import io.debezium.performance.dmt.schema.DatabaseEntry;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RegisterRestClient
@Path("/Main")
@ApplicationScoped
public interface DmtJsonService {
    /*@POST
    @Path("Insert")
    Response insert(JsonSchema schema);


    @POST
    @Path("Upsert")
    Response upsert(JsonSchema schema);

    @POST
    @Path("CreateTable")
    Response createTable(JsonSchema schema);*/

    @POST
    @Path("/CreateTableAndUpsert")
    Uni<Response> createAndUpsert(DatabaseEntry schema);
}
