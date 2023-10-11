package methal.akel.carquiz;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Question {

    String buttonAnswerText;
    String model;
    ArrayList<String> models;
    int questionimage;
    boolean isAsked;


    public Question(String buttonAnswer, int questionimage, String model, ArrayList<String> models) {

        this.buttonAnswerText = buttonAnswer;
        this.isAsked=false;
        this.model=model;
        this.models =new ArrayList<>(models);
        this.questionimage = questionimage;

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public ArrayList<String> getModels() {
        return models;
    }

    public void setModels(ArrayList<String> models) {
        this.models = models;
    }

    public int getQuestionimage() {
        return questionimage;
    }

    public void setQuestionimage(int questionimage) {
        this.questionimage = questionimage;
    }


    public boolean getIsAsked() {
        return isAsked;
    }

    public void setIsAsked(boolean isAsked) {
        this.isAsked=isAsked;
    }



    public String getButtonAnswerText() {
        return buttonAnswerText;
    }

    public void setButtonAnswerText(String buttonAnswerText) {
        this.buttonAnswerText = buttonAnswerText;
    }
}
