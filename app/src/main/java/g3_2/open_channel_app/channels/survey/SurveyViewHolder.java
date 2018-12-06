package g3_2.open_channel_app.channels.survey;

import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.view.View;
import android.widget.TextView;

import g3_2.open_channel_app.R;

public class SurveyViewHolder {

    public TextView survey_title;
    public TextView q1_question;
    public MaterialButton q1_ans1;
    public MaterialButton q1_ans2;
    public TextView q2_question;
    public TextView q2_answer;


    public SurveyViewHolder(@NonNull View view) {
        survey_title = view.findViewById(R.id.survey_title);
        q1_question = view.findViewById(R.id.q1_question);
        q1_ans1 = view.findViewById(R.id.q1_ans1);
        q1_ans2 = view.findViewById(R.id.q1_ans2);
        q2_question = view.findViewById(R.id.q2_question);
        q2_answer = view.findViewById(R.id.q2_answer);
    }
}
