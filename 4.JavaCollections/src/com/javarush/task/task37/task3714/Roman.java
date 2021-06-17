package com.javarush.task.task37.task3714;

public enum Roman {
    I(1), V(5), X(10), L(50), C(100), D(500), M(1000);
    private final int value;
    private Roman(int value) {
        this.value = value;
    }

    public int toInt() {
        return value;
    }
}
