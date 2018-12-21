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
   * Layout constructor
   * @param identifier layout identifier
   * @param type layout type
   */
  public Layout(String identifier, LayoutType type) {
    super(identifier);
    this.elements = new ArrayList<>();
    this.type = type;
  }

  /**
   * Get elements of layout
   * @return Element list of layout
   */
  public List<Element> getElements() {
    return elements;
  }

  /**
   * Set elements of layout
   * @param elements element list of layout
   * @return Layout
   */
  public Layout setElements(List<Element> elements) {
    this.elements = elements;
    return this;
  }

  /**
   * Add element to layout
   * @param element element
   * @return Layout element
   */
  public Layout addElement(Element element){
    this.elements.add(element);
    return this;
  }

  /**
   * Get layout type
   * @return LayoutType
   */
  public LayoutType getType() {
    return type;
  }

  /**
   * Set layout type
   * @param type type
   * @return Layout element
   */
  public Layout setType(LayoutType type) {
    this.type = type;
    return this;
  }

  /**
   * Get layout gap
   * @return gap
   */
  public Integer getGap() {
    return gap;
  }

  /**
   * Set layout gap
   * @param gap gap
   * @return Layout element
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
   * @return Layout element
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
