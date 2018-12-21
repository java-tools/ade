package com.almis.ade.api.util;

import com.almis.ade.api.bean.component.Image;
import net.sf.jasperreports.renderers.Renderable;
import net.sf.jasperreports.renderers.SimpleRenderToImageAwareDataRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URL;

/**
 * Image management
 * @author dfuentes
 */
public class ImageUtil {

  /**
   * Private constructor to hide the implicit one
   */
  private ImageUtil() {}

  /**
   * Image as input stream
   * @param imageBean Image bean
   * @return Image as stream
   * @throws IOException IOException exception
   */
  public static InputStream getImageAsInputStream(Image imageBean) throws IOException {
    if (imageBean.getImageURL() != null){
      RenderedImage image = downloadAndRenderImage(imageBean.getImageURL());
      return getRenderedImageAsInputStream(image);
    } else if(imageBean.getImageFile() != null){
      return new FileInputStream(imageBean.getImageFile());
    } else if (imageBean.getImageInputStream() != null) {
      return imageBean.getImageInputStream();
    }
    return null;
  }

  /**
   * Image as renderable
   * @param imageBean Image bean
   * @return Image as renderable
   */
  public static Renderable getImageAsRenderable(Image imageBean) {
    if (imageBean.getSVGImage() != null) {
      return getSvgImageAsRenderable(imageBean.getSVGImage());
    }
    return null;
  }

  private static InputStream getRenderedImageAsInputStream(RenderedImage image) throws IOException {
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    ImageIO.write(image,"png" ,os);
    return new ByteArrayInputStream(os.toByteArray());
  }

  /**
   * Svg as renderable
   * @param svgImage Svg image string
   * @return Svg as renderable
   */
  public static Renderable getSvgImageAsRenderable(String svgImage) {
    return SimpleRenderToImageAwareDataRenderer.getInstance(svgImage.getBytes());
  }

  private static RenderedImage downloadAndRenderImage(URL imageURL) throws IOException {
    java.awt.Image image = ImageIO.read(imageURL);
    BufferedImage bImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
    Graphics2D bImageGraphics = bImage.createGraphics();
    bImageGraphics.drawImage(image, null, null);

    return bImage;
  }
}
