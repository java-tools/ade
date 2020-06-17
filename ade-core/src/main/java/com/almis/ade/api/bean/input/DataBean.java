package com.almis.ade.api.bean.input;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Data bean
 *
 * @author dfuentes
 */
public class DataBean {

  private static final String VALUE = "value";

  private final Map<String, Object> singleValueMap;

  /**
   * Constructor
   */
  public DataBean() {
    this.singleValueMap = new HashMap<>();
  }

  /**
   * Constructor from JSON
   *
   * @param data JSON Object node
   */
  public DataBean(ObjectNode data) {
    ObjectMapper mapper = new ObjectMapper();
    singleValueMap = mapper.convertValue(data, new TypeReference<Map<String, Object>>() {
    });
  }

  /**
   * Add a value
   *
   * @param key   Key
   * @param value Value
   * @return this
   */
  public DataBean add(String key, Object value) {
    this.singleValueMap.put(key, value);
    return this;
  }

  /**
   * Get a value
   *
   * @param key Value key
   * @return Value
   */
  public Object getSingleValue(String key) {
    return this.singleValueMap.get(key);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DataBean dataBean = (DataBean) o;
    return String.valueOf(singleValueMap.get(VALUE)).equals(String.valueOf(dataBean.getSingleValue(VALUE)));
  }

  @Override
  public int hashCode() {
    return Objects.hash(singleValueMap);
  }
}
