package com.almis.ade.api.bean.component;

import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.VerticalImageAlignment;
import net.sf.jasperreports.renderers.Renderable;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

/**
 * Image class
 */
public class Image extends Element<Image> {

  private File imageFile;
  private InputStream imageInputStream;
  private String svgImage;
  private URL imageURL;
  private Integer size;
  private Text header;
  private Text footer;
  private Float scale = 1f;
  private AbstractSimpleExpression<Renderable> expression;

  /**
   * Image constructor
   * @param identifier image inditifier
   */
  public Image(@NotNull String identifier) {
    super(identifier);
  }

  /**
   * Initialize image element
   */
  @Override
  public void initialize() {
    this.setStyle(
            stl.style()
                    .setHorizontalImageAlignment(HorizontalImageAlignment.CENTER)
                    .setVerticalImageAlignment(VerticalImageAlignment.TOP)
    );
  }

  /**
   * Image scale
   * @return Scale
   */
  public Float getScale() {
    return scale;
  }

  /**
   * Image scale
   * @param scale Scale
   * @return this
   */
  public Image setScale(Float scale) {
    this.scale = scale;
    return this;
  }

  /**
   * Image sizes
   */
  public enum Size {
    VERY_SMALL (12), SMALL(50), MEDIUM(130), FULL_SIZE(531), FULL_SIZE_LANDSCAPE(778);

    private final int sizeValue;

    Size(int size) {
      this.sizeValue = size;
    }

    /**
     * Get image size
     * @return image size
     */
    public int getSize() {
      return sizeValue;
    }
  }

  /**
   * Retrieve image as file
   * @return Image as file
   */
  public File getImageFile() {
    return imageFile;
  }

  /**
   * Set image as file
   * @param imageFile Image as file
   * @return this
   */
  public Image setImageFile(File imageFile) {
    this.imageFile = imageFile;
    return this;
  }

  /**
   * Retrieve image as input stream
   * @return Image as input stream
   */
  public InputStream getImageInputStream() {
    return imageInputStream;
  }

  /**
   * Set image as input stream
   * @param imageInputStream Image as input stream
   * @return this
   */
  public Image setImageInputStream(InputStream imageInputStream) {
    this.imageInputStream = imageInputStream;
    return this;
  }

  /**
   * Retrieve image as svg
   * @return Image as svg
   */
  public String getSVGImage() {
    return svgImage;
  }

  /**
   * Set image as svg
   * @param svgImage Image as svg
   * @return this
   */
  public Image setSVGImage(String svgImage) {
    this.svgImage = svgImage;
    return this;
  }

  /**
   * Get image as URL
   * @return Image as URL
   */
  public URL getImageURL() {
    return imageURL;
  }

  /**
   * Set image as URL
   * @param imageURL Image as URL
   * @return this
   */
  public Image setImageURL(URL imageURL) {
    this.imageURL = imageURL;
    return this;
  }

  /**
   * Get image header
   * @return Image header
   */
  public Text getHeader() {
    return header;
  }

  /**
   * Set image header
   * @param header Image header
   * @return this
   */
  public Image setHeader(Text header) {
    this.header = header;
    return this;
  }

  /**
   * Get image footer
   * @return Image footer
   */
  public Text getFooter() {
    return footer;
  }

  /**
   * Set image footer
   * @param footer Image footer
   * @return this
   */
  public Image setFooter(Text footer) {
    this.footer = footer;
    return this;
  }

  /**
   * Get image size
   * @return Image size
   */
  public Integer getSize() {
    return size == null ? Size.FULL_SIZE.getSize() : size;
  }

  /**
   * Set image size
   * @param size Image size
   * @return this
   */
  public Image setSize(Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Get expression
   * @return AbstractSimpleExpression
   */
  public AbstractSimpleExpression<Renderable> getExpression() {
    return expression;
  }

  /**
   * Set expression
   * @param expression expression
   * @return Image element
   */
  public Image setExpression(AbstractSimpleExpression<Renderable> expression) {
    this.expression = expression;
    return this;
  }
}
