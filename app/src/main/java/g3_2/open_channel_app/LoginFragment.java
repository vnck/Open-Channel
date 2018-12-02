package g3_2.open_channel_app;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    EditText ic;
    EditText pw;
    final String TAG = "TAG";

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        ic = (EditText) view.findViewById(R.id.NRIC);
        pw = (EditText) view.findViewById(R.id.pw);

        final LoginActivity callerActivity = (LoginActivity)getActivity();

        MaterialButton nextButton = view.findViewById(R.id.login);

        // Snippet from "Navigate to the next Fragment" section goes here.
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (callerActivity.getSharedPreferences("pathToUser",0) != null) {
                    Toast.makeText(getContext(), "Congrats", Toast.LENGTH_LONG).show();
                    SharedPreferences.Editor editor = callerActivity.getSharedPreferences("pathToUser",0).edit();
                    editor.putString("getPath",ic.getText().toString());
                    editor.commit();
                }
                else {
                    Toast.makeText(getContext(), "Fail", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "you already in mate");
                }
                startActivity(new Intent(getActivity(), MainActivity.class));
                //TODO Logging in
                /**
                // if not already logged in
                if (callerActivity.getSharedPreferences("pathToUser",0).getString("userRef",null) == null) {
                    // how to get context ?
                    dB.login(ic.getText().toString(),pw.getText().toString(), getApplicationContext());
                }
                else{
                    // you are logged in as
                    Log.d(TAG, "DUDE");
                    SharedPreferences getter = getSharedPreferences("profileRef",0);
                    int size = getter.getAll().size();
                    Log.d(TAG, "size" + size);
                    Log.d(TAG, "oi" + dB.getSnap(getApplicationContext()).get("id"));
                }
                 **/
            }
        });


        // Clear error once more than 8 characters typed
//        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent){
//                if (isPasswordValid(passwordEditText.getText())){
//                    passwordTextInput.setError(null);
//                }
//                return false;
//            }
//        });vvv vvvv v

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        // TODO: Use the ViewModel
    }

//    private boolean isPasswordValid(@Nullable Editable text){
//        // placeholder password check
//        return text != null && text.length() >= 8;
//    }

}
