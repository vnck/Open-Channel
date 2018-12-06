package g3_2.open_channel_app.channels.survey;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import g3_2.open_channel_app.R;

public class SubFragmentActions extends Fragment {

    MaterialButton buttonTakeSurvey;


    public static SubFragmentActions newInstance() {
        return new SubFragmentActions();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.subfragment_actions, container, false);

        buttonTakeSurvey = view.findViewById(R.id.buttonTakeSurvey);
        buttonTakeSurvey.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SurveyActivity.class);
                startActivity(intent);
            }
        });



//        RecyclerView recyclerView = view.findViewById(R.id.my_channel_recycler_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
//        QuestionsAdapter adapter = new QuestionsAdapter(
//                getContext(),ChannelEntry.initProductEntryList(getResources(),R.raw.mychannels), SubFragmentActions.this);
//        recyclerView.setAdapter(adapter);
//        int largePadding = getResources().getDimensionPixelSize(R.dimen.oc_channel_grid_spacing);
//        int smallPadding = getResources().getDimensionPixelSize(R.dimen.oc_channel_grid_spacing_small);
//        recyclerView.addItemDecoration(new ChannelGridDecoration(largePadding, smallPadding));

        return view;
    }

}
