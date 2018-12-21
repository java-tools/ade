package com.almis.ade.api.bean.input;

import java.util.ArrayList;
import java.util.List;

/**
 * Checkbox radio question class
 * @author augarte
 */
public class CheckboxRadioQuestion {
  boolean checkBox = false;
  String question;
  List<CheckboxRadioAnswer> answerList = new ArrayList<>();
  
  /**
   * CheckboxRadioQuestion constructor
   * @param question question
   * @param checkBox checkBox
   */
  public CheckboxRadioQuestion (String question, boolean checkBox) {
    this.question = question;
    this.checkBox = checkBox;    
  }
  
  /**
   * Default constructor
   */
  public CheckboxRadioQuestion () {
      // Default constructor
  }

  /**
   * Flag to indicate if element is a checkbox
   * @return is checkbox
   */
  public boolean isCheckBox() {
      return checkBox;
  }

  /**
   * Set checkbox flag
   * @param checkBox checkbox flag
   */
  public void setIsCheckBox(boolean checkBox) {
      this.checkBox = checkBox;
  }

  /**
   * Get question
   * @return question
   */
  public String getQuestion() {
      return question;
  }

  /**
   * Set question
   * @param question question
   */
  public void setQuestion(String question) {
      this.question = question;
  }

  /**
   * Get answer list
   * @return answer list
   */
  public List<CheckboxRadioAnswer> getAnswerList() {
        return answerList;
    }

  /**
   * Set answer list
   * @param answerList answer list
   */
  public void setAnswerList(List<CheckboxRadioAnswer> answerList) {
        this.answerList = answerList;
    }
  
  
}
