/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.almis.ade.api.bean.input;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author augarte
 */
public class CheckboxRadioQuestion {
  boolean checkBox = false;
  String question;
  List<CheckboxRadioAnswer> answerList = new ArrayList<CheckboxRadioAnswer>();
  
  /**
   *
   * @param question
   * @param checkBox
   */
  public CheckboxRadioQuestion (String question, boolean checkBox) {
    this.question = question;
    this.checkBox = checkBox;    
  }
  
  /**
   *
   */
  public CheckboxRadioQuestion () {
      
  }

  /**
   *
   * @return
   */
  public boolean isCheckBox() {
      return checkBox;
  }

  /**
   *
   * @param checkBox
   */
  public void setIsCheckBox(boolean checkBox) {
      this.checkBox = checkBox;
  }

  /**
   *
   * @return
   */
  public String getQuestion() {
      return question;
  }

  /**
   *
   * @param question
   */
  public void setQuestion(String question) {
      this.question = question;
  }

  /**
   *
   * @return
   */
  public List<CheckboxRadioAnswer> getAnswerList() {
        return answerList;
    }

  /**
   *
   * @param answerList
   */
  public void setAnswerList(List<CheckboxRadioAnswer> answerList) {
        this.answerList = answerList;
    }
  
  
}
