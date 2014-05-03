package org.unitedid.auth.client;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.unitedid.auth.client.impl.Factor;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class PasswordFactor extends Factor {
    @Expose
    @SerializedName("H1")
    private String passwordHash;
    private String salt;


    public PasswordFactor(String password, String credentialId)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        this(password, credentialId, 10);
    }

    public PasswordFactor(String password, String credentialId, int rounds)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        this(password, credentialId, BCrypt.gensalt(rounds));
    }

    public PasswordFactor(String password, String credentialId, String salt)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        this.type = "password";
        this.credentialId = credentialId;
        this.salt = salt;
        String bcryptHash = BCrypt.hashpw(sha256PreHash(password), salt);
        passwordHash = bcryptHash.substring(salt.length());
    }

    public String getSalt() {
        return salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Due to limitations of BCrypt() max password length of 55 characters we pre hash the password using
     * sha256 before passing it onto BCrypt() to allow for longer passwords.
     *
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private String sha256PreHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String result = "";
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes("UTF-8"));

        for (int i = 0; i < hash.length; i++) {
            result += Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1);
        }

        return result;
    }

    public String getRandomNonce() throws NoSuchAlgorithmException {
        SecureRandom rand = SecureRandom.getInstance("SHA1PRNG");
        byte[] randomBytes = new byte[6];
        rand.nextBytes(randomBytes);

        String result = "";
        for (int i = 0; i < randomBytes.length; i++) {
            result += Integer.toString((randomBytes[i] & 0xff) + 0x100, 16).substring(1);
        }

        return result;
    }

    public Boolean verify(String plaintext, String hash) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return BCrypt.checkpw(sha256PreHash(plaintext), hash);
    }

    /*@Override
    public String toString() {
        return "PasswordFactor [H1=" + passwordHash
                + ", type=" + credentialType
                + ", credentialId=" + credentialId +"]";
    } */

    @Override
    public String toString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
