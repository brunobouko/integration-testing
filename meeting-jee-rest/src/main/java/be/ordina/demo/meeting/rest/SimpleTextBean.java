package be.ordina.demo.meeting.rest;
@SuppressWarnings("unused")//setters and getters needed for json marshalling
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleTextBean that = (SimpleTextBean) o;

        return text.equals(that.text);
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }
}
