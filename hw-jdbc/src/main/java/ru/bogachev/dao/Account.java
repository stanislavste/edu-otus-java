package ru.bogachev.dao;

import ru.bogachev.annotations.Id;

public class Account {
    @Id
    private long no;

    private String type;
    private double rest;

    public Account(long no, String type, double rest) {
        this.no = no;
        this.type = type;
        this.rest = rest;
    }

    public long getNo() {
        return no;
    }

    public String getType() {
        return type;
    }

    public double getRest() {
        return rest;
    }

    @Override
    public String toString() {
        return "Account{" +
                "no=" + no +
                ", type='" + type + '\'' +
                ", rest=" + rest +
                '}';
    }
}
