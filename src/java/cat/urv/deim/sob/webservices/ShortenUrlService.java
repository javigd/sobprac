/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.webservices;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.util.Config;
import cat.urv.deim.sob.util.URLConverter;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author javigd
 */
@Stateless
@Path("/shorten")
public class ShortenUrlService {

    /**
     *
     * @param longUrl
     * @param userId
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String shortenUrl(@QueryParam("longUrl") String longUrl, @QueryParam("userId") String userId) {
        /* Make sure URL is long enough, raise exception notifying user otherwise */
        if (longUrl.length() < Config.DEFAULT_MIN_URL_LENGTH) {
            return " ";
        }
        /* Get a Long value resulting from a combined hash between long URL and user ID */
        Long combinedHashVal;
        try {
            combinedHashVal = URLConverter.getCombinedHashValue(longUrl, userId);
            String shortenedUrl = URLConverter.convert(Config.DEFAULT_CONVERT_BASE, combinedHashVal);
            /* Make sure short URL provided is short enough, trim it otherwise */
            if (shortenedUrl.length() > Config.MAX_SHORTENED_URL_LENGTH) {
                shortenedUrl = shortenedUrl.substring(0, Config.MAX_SHORTENED_URL_LENGTH - 1);
            }
            System.out.println(shortenedUrl);
            return shortenedUrl;
        } catch (SOBException ex) {
            return " ";
        }
    }
}
