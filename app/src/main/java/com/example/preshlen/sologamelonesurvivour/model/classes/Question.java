package com.example.preshlen.sologamelonesurvivour.model.classes;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Question implements Serializable {
    int questionId;
    private String text;

    private List<Answer> answers;

    public Question(String text) {

        this.text = text;
      //  answers = Constants.defaultQuestions.get(text);
    }

    public String getText() {
        return this.text;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void showAnswers(){
        Collections.shuffle(this.answers);
        System.out.println(answers);
    }

    public Answer getRightAnswer() {
        for(Answer answer: this.answers){
            if(answer.isRight()) {
                return answer;
            }
        }
        return null;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return text;
    }
}