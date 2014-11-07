/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.session;

import cat.urv.deim.sob.util.Config;
import javax.servlet.http.Cookie;

/**
 *
 * @author javigd
 */
public class SOBCookie extends Cookie {

    public SOBCookie(String name, String value) {
        super(name, value);
        super.setMaxAge(Config.SESSION_MAX_TIME);
    }
}
