package methal.akel.carquiz;

import java.util.Comparator;

public class Score {

    private String name;
    private int puan;

    public Score() {
    }

    public Score(String name, int puan) {
        this.name = name;
        this.puan = puan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPuan() {
        return puan;
    }

    public void setPuan(int puan) {
        this.puan = puan;
    }

    public static Comparator<Score> ScoreComp = new Comparator<Score>() {

        public int compare(Score s1, Score s2) {

            int rollno1 = s1.getPuan();
            int rollno2 = s2.getPuan();

            /*For ascending order*/
            //return rollno1-rollno2;

            /*For descending order*/
            return rollno2-rollno1;
        }};
}
