package com.almis.ade.api.engine.jasper.generation.builder.mapper;

import com.almis.ade.api.bean.component.Element;
import com.almis.ade.api.engine.jasper.generation.builder.component.element.ElementBuilder;
import com.almis.ade.api.enumerate.Section;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Builder mapper
 * @author dfuentes
 */
public class BuilderMapper {

  private Map<Section, ElementBuilder> sectionBuilders;
  private Map<Class<? extends Element>, ElementBuilder> elementBuilders;

  private static BuilderMapper instance = null;
  private BuilderMapper() {
    this.elementBuilders = new HashMap<>();
    this.sectionBuilders = new EnumMap<>(Section.class);
  }

  /**
   * Get builder mapper instance
   * @return Builder mapper instance
   */
  public static BuilderMapper getInstance() {
    if (instance == null) {
      instance = new BuilderMapper();
    }
    return instance;
  }

  /**
   * Get mapper builders
   * @return Builders
   */
  public Map<Class<? extends Element>, ElementBuilder> getBuilders() {
    return elementBuilders;
  }

  /**
   * Get builder
   * @param elementBean Get a builder
   * @return builder
   */
  public ElementBuilder getBuilder(Class<? extends Element> elementBean) {
    return elementBuilders.get(elementBean);
  }

  /**
   * Add a builder
   * @param elementBean Builder bean
   * @param elementBuilder Builder
   * @return this
   */
  public BuilderMapper addBuilder(Class<? extends Element> elementBean, ElementBuilder elementBuilder) {

    //Set builder mapper value
    this.elementBuilders.put(elementBean, elementBuilder);
    return this;
  }

  /**
   * Set builders
   * @param documentElementBuilders builders
   * @return this
   */
  public BuilderMapper setBuilders(Map<Class<? extends Element>, ElementBuilder> documentElementBuilders) {

    //Set builder mapper value
    this.elementBuilders = documentElementBuilders;
    return this;
  }

  /**
   * Get section builders
   * @return Builders
   */
  public Map<Section, ElementBuilder> getSectionBuilders() {
    return sectionBuilders;
  }

  /**
   * Get section builder
   * @param section Section
   * @return Builder
   */
  public ElementBuilder getSectionBuilder(Section section) {
    return sectionBuilders.get(section) == null ? sectionBuilders.get(Section.DEFAULT) : sectionBuilders.get(section);
  }

  /**
   * Add a section builder
   * @param section Section
   * @param sectionBuilder Builder
   * @return this
   */
  public BuilderMapper addSectionBuilder(Section section, ElementBuilder sectionBuilder) {

    //Set builder mapper value
    this.sectionBuilders.put(section, sectionBuilder);
    return this;
  }

  /**
   * Set all section builders
   * @param sectionBuilders Section builders
   * @return this
   */
  public BuilderMapper setSectionBuilders(Map<Section, ElementBuilder> sectionBuilders) {
    //Set builder mapper value
    this.sectionBuilders = sectionBuilders;
    return this;
  }
}
