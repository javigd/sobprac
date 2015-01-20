/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUser;
import cat.urv.deim.sob.util.Config;
import java.util.List;
import java.util.Random;
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
import javax.ws.rs.QueryParam;

/**
 *
 * @author javigd
 */
@Stateless
@Path("/user")
public class SOBUserFacadeREST extends AbstractFacade<SOBUser> {

    @PersistenceContext(unitName = "")
    private EntityManager em;

    public SOBUserFacadeREST() {
        super(SOBUser.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public SOBUser create(SOBUser entity) {
        return super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, SOBUser entity) {
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
    public SOBUser find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<SOBUser> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<SOBUser> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("ticket")
    @Produces({"application/xml", "application/json"})
    public SOBUser ticket(@QueryParam("id") Long id) {
        // Generate and save a ticket
        Random random = new Random();
        // Generate a ticket
        Long ticket = Math.abs(random.nextLong());
        // Save ticket in DB
        return super.setTicket(id, ticket);
    }

    @POST
    @Path("resetpass")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public SOBUser resetPassword(SOBUser entity) {
        return super.resetPassword(entity.getId(), entity.getResetTicket(), entity.getPassword());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
