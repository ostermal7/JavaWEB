package entity;

public class StudentDiscAndMark {
    private int disciplineId;
    private String disciplineName;
    private int mark;

    public StudentDiscAndMark() {
    }

    public StudentDiscAndMark(int disciplineId, String disciplineName, int mark) {
        this.disciplineId = disciplineId;
        this.disciplineName = disciplineName;
        this.mark = mark;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentDiscAndMark that = (StudentDiscAndMark) o;

        if (disciplineId != that.disciplineId) return false;
        if (mark != that.mark) return false;
        return disciplineName != null ? disciplineName.equals(that.disciplineName) : that.disciplineName == null;
    }

    @Override
    public int hashCode() {
        int result = disciplineId;
        result = 31 * result + (disciplineName != null ? disciplineName.hashCode() : 0);
        result = 31 * result + mark;
        return result;
    }

    @Override
    public String toString() {
        return "StudentDiscAndMark{" +
                "disciplineId=" + disciplineId +
                ", disciplineName='" + disciplineName + '\'' +
                ", mark=" + mark +
                '}';
    }
}
