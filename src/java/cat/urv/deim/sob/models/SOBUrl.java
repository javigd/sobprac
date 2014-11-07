/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.models;

import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import cat.urv.deim.sob.util.Config;
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
    @SequenceGenerator(name = "urlSeq", allocationSize = 1, initialValue = 10000000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "urlSeq")
    private Long id;
    private String longUrl;
    private String shortUrl;
    private Long userId;
    private Long nvisits;

    public SOBUrl() {
        super();
    }

    public SOBUrl(Long id, String longUrl, String shortURL, Long userId, Long nvisits) {
        this.id = id;
        this.longUrl = longUrl;
        this.shortUrl = shortURL;
        this.userId = userId;
        this.nvisits = nvisits;
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

    public String getMessage() {
        return toString();
    }

    public Long getNvisits() {
        return nvisits;
    }

    public void setNvisits(Long nvisits) {
        this.nvisits = nvisits;
    }

    @Override
    public String toString() {

        return "\nLongUrl:\t\t" + getLongUrl() + "\n"
                + "ShortUrl:" + getShortUrl() + "\n"
                + "user:" + getUserId() + "\n";
    }

    public void validate() throws SOBException {
        if (longUrl == null || shortUrl == null || userId == null
                || longUrl.equals("") || shortUrl.equals("")) {
            throw new SOBException(SOBError.URL_UNVALID);
        }
        if (shortUrl.length() < Config.MIN_SHORTENED_URL_LENGTH) {
            throw new SOBException(SOBError.SHORT_URL_TOO_SHORT);
        }
        if (shortUrl.length() > Config.MAX_SHORTENED_URL_LENGTH) {
            throw new SOBException(SOBError.SHORT_URL_TOO_LONG);
        }
        if (containsWeirdChars(shortUrl)) {
            throw new SOBException(SOBError.SHORT_URL_BAD_CHARS);
        }
    }

    public boolean isValid() {
        return !(longUrl == null || shortUrl == null || userId == null);
    }
    
    private boolean containsWeirdChars(String str) {
        str = str.replaceAll("[a-zA-Z0-9]", "");
        return str.length() > 0;
    }
}
