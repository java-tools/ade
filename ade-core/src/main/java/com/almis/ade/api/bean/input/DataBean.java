package com.almis.ade.api.bean.input;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data bean
 * @author dfuentes
 */
public class DataBean {

  private Map<String,Object> singleValueMap;
  private Map<String,List<Object>> multiValueMap;
  private Map<String,Map<Object,Object>> matrixMap;

  /**
   * Constructor
   */
  public DataBean(){
    init();
  }

  /**
   * Constructor from JSON
   * @param data JSON Object node
   */
  public DataBean(ObjectNode data) {
    init();
    JsonNode label = data.get("label");
    JsonNode value = data.get("value");
    JsonNode icon = data.get("icon");
    JsonNode image = data.get("image");
    JsonNode style = data.get("style");
    if (label != null) {
      add("label", label.asText());
    }
    if (value != null) {
      add("value", value.asText());
    }
    if (icon != null) {
      add("icon", icon.asText());
    }
    if (image != null) {
      add("image", image.asText());
    }
    if (style != null) {
      add("style", style.asText());
    }
  }

  private void init() {
    this.singleValueMap = new HashMap<>();
    this.multiValueMap = new HashMap<>();
    this.matrixMap = new HashMap<>();
  }

  /**
   * Add a value
   * @param key Key
   * @param value Value
   * @return this
   */
  public DataBean add(String key, Object value){
    this.singleValueMap.put(key,value);
    return this;
  }

  /**
   * Get a value
   * @param key Value key
   * @return Value
   */
  public Object getSingleValue(String key) {
    return this.singleValueMap.get(key);
  }

  /**
   * add a list of values
   * @param key key
   * @param values list of values
   * @return this
   */
  public DataBean add(String key, List<Object> values){
    this.multiValueMap.put(key,values);
    return this;
  }

  /**
   * Add a map of values
   * @param key key
   * @param values map of values
   * @return this
   */
  public DataBean add(String key, Map<Object,Object> values){
    this.matrixMap.put(key,values);
    return this;
  }
}
