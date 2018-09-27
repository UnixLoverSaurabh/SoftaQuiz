package com.company.Model;

import javafx.scene.control.Button;

public class QuestionNumberButtons {
    private Button button;

    public QuestionNumberButtons(Button button) {
        this.button = button;
        this.button.setText("Question");
    }

    public Button getButton(){
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
