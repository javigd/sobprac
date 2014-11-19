/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.db;

import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUrl;
import java.util.List;

/**
 *
 * @author Max
 */
public interface UrlDAO {

    public SOBUrl get(Long urlId) throws SOBException;

    public SOBUrl add(SOBUrl url) throws SOBException;
    
    public SOBUrl updateVisits(SOBUrl url) throws SOBException;

    public SOBUrl getUrlFromShort(String urlId) throws SOBException;
    
    public List<SOBUrl> getUrlsByUserId(Long userId) throws SOBException;
    
    public List<SOBUrl> getUrlsByOffset(Long userId, int offset, int setSize) throws SOBException;

    public Long getUrlCountByUser(Long userId) throws SOBException;
       
}
