package com.project250.applock.passStrings;

import android.content.Context;

import io.paperdb.Paper;

public class password {
    private String PASS_KEY = "PASSWORD KEY";
    public String PATTERN = "PATTERN SET";
    public String CONFIRM = "Draw the pattern again to confirm";
    public String INCORRECT = "Please try again";
    public String FIRST_USE = "Draw pattern to Unlock";
    public String SCHEMA = "You must connect at least 4 dots !";
    public boolean isFirst = true;

    public password(Context context){
        Paper.init(context);
    }

    public String getPASS_KEY() {
        return Paper.book().read(PASS_KEY);
    }

    public void setPASS_KEY(String PASS) {
        Paper.book().write(PASS_KEY,PASS);
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isCorrect(String PASS){
        return PASS.equals(getPASS_KEY());
    }
}
