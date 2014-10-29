/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Max
 */
@Entity
public class SOBUrl implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String longUrl;
    private String shortUrl;
    private Long user;
    private String useremail;

    public SOBUrl() {
       super();
    }

    public SOBUrl(String longUrl, String shortURL, Long user, String useremail) {
        this.longUrl = longUrl;
        this.shortUrl = shortURL;
        this.user = user;
        this.useremail = useremail;
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

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
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
                + "user:" + getUser() + "\n"
                + "useremail:" + getUseremail() + "\n";
    }
    
    public boolean isValid() {
        return !(longUrl == null || shortUrl == null || user == null || useremail == null);
        
    }  
}
