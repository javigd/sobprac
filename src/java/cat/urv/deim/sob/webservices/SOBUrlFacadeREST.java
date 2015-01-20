/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.webservices;

import cat.urv.deim.sob.models.SOBUrl;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author javigd
 */
@Stateless
@Path("/url")
public class SOBUrlFacadeREST extends AbstractFacade<SOBUrl> {
    @PersistenceContext(unitName = "")
    private EntityManager em;

    public SOBUrlFacadeREST() {
        super(SOBUrl.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(SOBUrl entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, SOBUrl entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public SOBUrl find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Path("visit/{id}")
    @Produces({"application/xml", "application/json"})
    public void visit(@PathParam("id") Long id) {
        super.visit(id);
    }

    
    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<SOBUrl> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<SOBUrl> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
