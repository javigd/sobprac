/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.beans;

import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javigd
 */
public class FormHandler {

    private String username;
    private String email;
    private String password;
    private String passwordRepeat;
    private byte[] encryptedPassword;

    public FormHandler() {

    }

    public FormHandler(String username, String email, String password, String passwordRepeat) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
    }
    
    public boolean validate() throws SOBException {
        if (!isValid()) {
            throw new SOBException(SOBError.INCOMPLETE_FIELDS);
        }
        else if (!password.equals(passwordRepeat)) {
            throw new SOBException(SOBError.REPEAT_PASSWORD);
        }
        return true;
    }
    
    public boolean validateLogin() throws SOBException { 
        if (!isValidLogin()) {
            throw new SOBException(SOBError.INCOMPLETE_FIELDS);
        }
        return true;
    }
    
    public byte[] encryptPassword () {
        MessageDigest md = null;
        
        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(FormHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        byte[] passbytes = this.password.getBytes();
        byte[] encpass = md.digest(passbytes);
        
        // Erase data related to plaintext passwords
        this.password = null;
        this.passwordRepeat = null;
        
        setEncryptedPassword(encpass);
        return encpass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public byte[] getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(byte[] encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
    
    public boolean isValid() {
        return !(username == null || email == null || password == null
                || username.equals("") || email.equals("") || password.equals(""));
    }
    
    public boolean isValidLogin() {
        return !(email == null || password == null
                || email.equals("") || password.equals(""));
    }
}
