/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.util;

import cat.urv.deim.sob.exceptions.SOBError;
import cat.urv.deim.sob.exceptions.SOBException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author javigd
 */
public class URLConverter {

    public static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // TODO: Fix 0 case
    /**
     * Convert the given long value to an equivalent String representing the
     * value in base 36 or 62.
     *
     * @param base
     * @param decimalNumber
     * @return
     * @throws SOBException
     */
    public static String convert(int base, long decimalNumber) throws SOBException {
        String tempVal = decimalNumber == 0 ? "0" : "";
        long mod;

        /* Restrict conversion to Base36 or Base62 */
        if (base != 36 && base != 62) {
            throw new SOBException(SOBError.BASE_NOT_SUPPORTED);
        }

        while (decimalNumber != 0) {
            mod = decimalNumber % base;
            tempVal = CHARACTERS.substring((int) mod, (int) mod + 1) + tempVal;
            decimalNumber = decimalNumber / base;
        }
        return tempVal;
    }

    public static Long getCombinedHashValue(String longUrl, String userId) throws SOBException {
        Long combinedHashValue;
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("MD5");
            md.reset();
        } catch (NoSuchAlgorithmException ex) {
            throw new SOBException(SOBError.INTERNAL_SERVER_ERROR);
        }

        // Get bytes and get the hash of the merged components
        byte[] urlBytes = longUrl.getBytes();
        byte[] userBytes = userId.getBytes();
        byte[] merge = ByteUtils.combine(urlBytes, userBytes);
        byte[] combinedHash = md.digest(merge);
        
        if (combinedHash.length > Config.TRIM_BYTEARRAY_SIZE) {
            combinedHash = ByteUtils.trimByteArray(combinedHash, Config.TRIM_BYTEARRAY_SIZE);
        }
        
        combinedHashValue = ByteUtils.bytesToLong(combinedHash);
        
        return Math.abs(combinedHashValue);
    }
}
