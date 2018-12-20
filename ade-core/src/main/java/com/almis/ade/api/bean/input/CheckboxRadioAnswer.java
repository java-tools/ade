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
   *
   * @param text
   * @param checked
   */
  public CheckboxRadioAnswer (String text, boolean checked) {
    this.text = text;
    this.checked = checked;
  }

  /**
   *
   * @return
   */
  public String getText() {
      return text;
  }

  /**
   *
   * @param text
   */
  public void setText(String text) {
      this.text = text;
  }

  /**
   *
   * @return
   */
  public boolean isChecked() {
      return checked;
  }

  /**
   *
   * @param checked
   */
  public void setChecked(boolean checked) {
      this.checked = checked;
  }
}
