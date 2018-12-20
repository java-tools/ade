package com.almis.ade.api.bean.component;

import com.almis.ade.api.enumerate.LayoutType;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dfuentes
 */
public class Layout extends Element<Layout> {

  private List<Element> elements;
  private LayoutType type;
  private Integer gap;
  private Integer width;
  private Integer height;

  /**
   *
   * @param identifier
   * @param type
   */
  public Layout(String identifier, LayoutType type) {
    super(identifier);
    this.elements = new ArrayList<>();
    this.type = type;
  }

  /**
   *
   * @return
   */
  public List<Element> getElements() {
    return elements;
  }

  /**
   *
   * @param elements
   * @return
   */
  public Layout setElements(List<Element> elements) {
    this.elements = elements;
    return this;
  }

  /**
   *
   * @param element
   * @return
   */
  public Layout addElement(Element element){
    this.elements.add(element);
    return this;
  }

  /**
   *
   * @return
   */
  public LayoutType getType() {
    return type;
  }

  /**
   *
   * @param type
   * @return
   */
  public Layout setType(LayoutType type) {
    this.type = type;
    return this;
  }

  /**
   *
   * @return
   */
  public Integer getGap() {
    return gap;
  }

  /**
   *
   * @param gap
   * @return
   */
  public Layout setGap(Integer gap) {
    this.gap = gap;
    return this;
  }

  /**
   * Get witdth
   * @return Width
   */
  public Integer getWidth() {
    return width;
  }

  /**
   * Set width
   * @param width Width
   * @return this
   */
  public Layout setWidth(Integer width) {
    this.width = width;
    return this;
  }

  /**
   * Get height
   * @return Height
   */
  public Integer getHeight() {
    return height;
  }

  /**
   * Set height
   * @param height Height
   * @return this
   */
  public Layout setHeight(Integer height) {
    this.height = height;
    return this;
  }
}
