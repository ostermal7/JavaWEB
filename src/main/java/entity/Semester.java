package entity;

public class Semester {
    private String semName;
    private String duration;
    private boolean status= true;
    private int semId;

    public int getSemId() {
        return semId;
    }

    public void setSemId(int semId) {
        this.semId = semId;
    }

    public Semester() {
    }

    public Semester(String semName, String duration, boolean status, int semId) {
        this.semName = semName;
        this.duration = duration;
        this.status = status;
        this.semId = semId;
    }

    public String getSemName() {
        return semName;
    }

    public void setSemName(String semName) {
        this.semName = semName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Semester semester = (Semester) o;

        if (status != semester.status) return false;
        if (semId != semester.semId) return false;
        if (semName != null ? !semName.equals(semester.semName) : semester.semName != null) return false;
        return duration != null ? duration.equals(semester.duration) : semester.duration == null;
    }

    @Override
    public int hashCode() {
        int result = semName != null ? semName.hashCode() : 0;
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + semId;
        return result;
    }

    @Override
    public String toString() {
        return "Semester{" +
                "semName='" + semName + '\'' +
                ", duration='" + duration + '\'' +
                ", status=" + status +
                ", semId=" + semId +
                '}';
    }
}
