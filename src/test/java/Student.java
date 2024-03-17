public class Student {
    String name;
    int Score;
    int ID;
    String describe;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Student(String name, int score, int ID, String describe) {
        this.name = name;
        Score = score;
        this.ID = ID;
        this.describe = describe;
    }
}
