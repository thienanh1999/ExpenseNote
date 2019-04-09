package com.example.jinny.expensesnote.model;

public class ExpenseModel {
    private String note = "";
    private long amount;
    private int id;
    private long time;
    private int user;

    public ExpenseModel() {
    }

    public ExpenseModel(long amount, int id, long time, int user) {
        this.amount = amount;
        this.id = id;
        this.time = time;
        this.user = user;
    }

    public ExpenseModel(String note, long amount, int id, long time, int user) {
        this.note = note;
        this.amount = amount;
        this.id = id;
        this.time = time;
        this.user = user;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getUser() { return user;}

    public long getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ExpenseModel{" +
                "note='" + note + '\'' +
                ", amount=" + amount +
                ", id=" + id +
                ", time=" + time +
                ", user=" + user +
                '}';
    }
}
