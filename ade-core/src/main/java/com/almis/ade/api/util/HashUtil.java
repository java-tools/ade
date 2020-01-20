package com.almis.ade.api.util;

import lombok.extern.log4j.Log4j2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author dfuentes
 */
@Log4j2
public class HashUtil {

  /**
   * Private constructor to avoid instantiation
   */
  private HashUtil() {
  }

  /**
   * Hash an object
   *
   * @param o Object
   * @return Object hash
   */
  public static String hashObject(Object o) {
    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
         ObjectOutput out = new ObjectOutputStream(bos)) {
      out.writeObject(o);
      out.flush();
      byte[] data = bos.toByteArray();

      return sha256(data);
    } catch (IOException | NoSuchAlgorithmException exc) {
      log.error("Error generating HASH - {}", o, exc);
    }
    return null;
  }

  /**
   * Hash with SHA256
   *
   * @param data Data
   * @return Data hash
   * @throws NoSuchAlgorithmException NoSuchAlgorithmException exception
   */
  public static String sha256(byte[] data) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(data); // Change this to "UTF-16" if needed
    byte[] digest = md.digest();

    return String.format("%064x", new java.math.BigInteger(1, digest));
  }
}