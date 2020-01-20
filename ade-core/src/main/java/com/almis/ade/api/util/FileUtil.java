package com.almis.ade.api.util;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * File utilities
 */
@Log4j2
public class FileUtil {

  /**
   * Private constructor to avoid instantiation
   */
  private FileUtil() {
  }

  /**
   * Retrieve a file from an URL
   *
   * @param url file url
   * @return input stream of file
   */
  public static InputStream getFileFromURL(URL url) {
    try {
      return url.openStream();
    } catch (IOException exc) {
      log.error("Error opening url Stream - {}", url, exc);
    }

    return null;
  }

  /**
   * Retrieve a file from an URL
   *
   * @param path File path
   * @return Resource as stream
   */
  public static InputStream getResourceAsStream(String path) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
  }
}
