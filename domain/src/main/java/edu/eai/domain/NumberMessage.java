package edu.eai.domain;

import java.io.Serializable;

public class NumberMessage implements Serializable {

    private int number1, number2;

    public NumberMessage(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public NumberMessage() {
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    @Override
    public String toString() {
        return "NumberMessage{" +
                "number1=" + number1 +
                ", number2=" + number2 +
                '}';
    }
}
