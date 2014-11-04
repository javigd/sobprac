/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.models;

import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Max
 */
@Entity
public class SOBUrl implements Serializable {

    @Id
    @SequenceGenerator( name = "urlSeq", allocationSize = 1, initialValue = 10000000 )
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "urlSeq" )
    private Long id;
    private String longUrl;
    private String shortUrl;
    private Long userId;
    private String useremail;

    public SOBUrl() {
       super();
    }

    public SOBUrl(Long id, String longUrl, String shortURL, Long userId, String useremail) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortURL;
        this.userId = userId;
        this.useremail = useremail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getMessage(){
        return toString();
    }
    
    @Override
    public String toString() {
        
        return "\nLongUrl:\t\t" + getLongUrl() + "\n"
                + "ShortUrl:" + getShortUrl() + "\n"
                + "user:" + getUserId() + "\n"
                + "useremail:" + getUseremail() + "\n";
    }
    
    public void validate() throws SOBException {
        if(longUrl == null || useremail == null) {
            throw new SOBException(SOBError.URL_UNVALID);
        }
    }
    
    public boolean isValid() {
        return !(longUrl == null || shortUrl == null || userId == null || useremail == null);  
    }
}
