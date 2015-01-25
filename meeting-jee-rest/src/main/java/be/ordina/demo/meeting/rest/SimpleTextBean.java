package be.ordina.demo.meeting.rest;

public class SimpleTextBean {
    private String text;

    public SimpleTextBean(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
