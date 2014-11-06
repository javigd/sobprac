/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.persistence;

import cat.urv.deim.sob.beans.UrlBean;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.models.SOBUrl;
import java.util.List;

/**
 *
 * @author Max
 */
public interface IUrlHandler {

    public String newUrl(SOBUrl url) throws SOBException;
    
    public List<UrlBean> getUserUrls(String userId) throws SOBException;

}
