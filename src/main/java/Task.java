public class Task {
    protected String title;
    protected boolean played;

    public Task(String title) {
        this.title = title;
        this.played = false;
    }

    public String getStatusIcon() {
        return played ? "X" : " ";
    }

    public void markAsPlayed() {
        this.played = true;
    }

    public void markAsUnplayed() {
        this.played = false;
    }

    public boolean isPlayed() {
        return played;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + title;
    }

}

