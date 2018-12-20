package com.almis.ade.api.util;

import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * File utilities
 */
public class FileUtil {

  /**
   * Private constructor to avoid instantiation
   */
  private FileUtil() {}

  /**
   * Retrieve a file from an URL
   * @param url
   * @return
   */
  public static InputStream getFileFromURL(URL url){
    try {
      return url.openStream();
    } catch (IOException exc) {
      LogManager.getLogger(FileUtil.class).error("Error opening url Stream - {0}", url, exc);
    }

    return null;
  }

  /**
   * Retrieve a file from an URL
   * @param path File path
   * @return Resource as stream
   */
  public static InputStream getResourceAsStream(String path){
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
  }
}
