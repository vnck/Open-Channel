package g3_2.open_channel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.w3c.dom.Text;

/**
 * Fragment representing the login screen for Shrine.
 */
public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.oc_login_fragment, container, false);

        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);

        MaterialButton nextButton = view.findViewById(R.id.next_button);

        // Snippet from "Navigate to the next Fragment" section goes here.
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(!isPasswordValid(passwordEditText.getText())){
                    passwordTextInput.setError(getString(R.string.oc_error_password));
                } else {
                    passwordTextInput.setError(null); // clear error
                    ((NavigationHost) getActivity()).navigateTo(new ChannelGridFragment(), false); // Navigate to the next Fragment
                }
            }
        });

        // Clear error once more than 8 characters typed
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent){
                if (isPasswordValid(passwordEditText.getText())){
                    passwordTextInput.setError(null);
                }
                return false;
            }
        });

        return view;
    }

    // "isPasswordValid" from "Navigate to the next Fragment" section method goes here

    private boolean isPasswordValid(@Nullable Editable text){
        // placeholder password check
        return text != null && text.length() >= 8;
    }
}
