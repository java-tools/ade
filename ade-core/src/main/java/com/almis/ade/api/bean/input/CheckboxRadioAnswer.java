/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almis.ade.api.bean.input;

/**
 *
 * @author augarte
 */
public class CheckboxRadioAnswer {
  String text;
  boolean checked;

  /**
   * CheckboxRadioAnswer constructor
   * @param text text of checkbox or radiobutton
   * @param checked flag if is check
   */
  public CheckboxRadioAnswer (String text, boolean checked) {
    this.text = text;
    this.checked = checked;
  }

  /**
   * Get text
   * @return text
   */
  public String getText() {
      return text;
  }

  /**
   * Set text
   * @param text text
   */
  public void setText(String text) {
      this.text = text;
  }

  /**
   * Is checked
   * @return flag is checked
   */
  public boolean isChecked() {
      return checked;
  }

  /**
   * Set check
   * @param checked enable check
   */
  public void setChecked(boolean checked) {
      this.checked = checked;
  }
}
