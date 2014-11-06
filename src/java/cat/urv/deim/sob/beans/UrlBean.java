/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.beans;

/**
 *
 * @author Max
 */
public class UrlBean {

    private String longUrl;
    private String shortUrl;
    private Long nvisits;

    public UrlBean() {
    }

    public UrlBean(String longUrl, String shortURL, Long nvisits) {
        this.longUrl = longUrl;
        this.shortUrl = shortURL;
        this.nvisits = nvisits;
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
    
    public String getMessage(){
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
                + "ShortUrl:" + getShortUrl() + "\n";
    }
}
