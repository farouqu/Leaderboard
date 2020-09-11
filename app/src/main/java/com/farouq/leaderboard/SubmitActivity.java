package com.farouq.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.farouq.leaderboard.services.APIClient;
import com.farouq.leaderboard.services.APIInterface;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public class SubmitActivity extends AppCompatActivity {
    private String mFirstName;
    private String mLastName;
    private String mEmailAddress;
    private String mGithubLink;

    private EditText mFirstNameEdit;
    private EditText mLastNameEdit;
    private EditText mEmailAddressEdit;
    private EditText mGithubLinkEdit;

    private ImageButton mBackButton;
    private Button mSubmitButton;

    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        mFirstNameEdit = findViewById(R.id.editTextTextFirstName);
        mLastNameEdit = findViewById(R.id.editTextTextLastName);
        mEmailAddressEdit = findViewById(R.id.editTextTextEmailAddress);
        mGithubLinkEdit = findViewById(R.id.editTextTextProjectLink);

        mBackButton = findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mSubmitButton = findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitFormData();
            }
        });

    }

    private void submitFormData(){
        if(verifyAllInputs()){
            showConfirmationDialog();
        }
    }

    private void showConfirmationDialog(){
        mDialog = new Dialog(SubmitActivity.this);
        mDialog.setContentView(R.layout.dialog_confirm);

        Button yesButton = mDialog.findViewById(R.id.submit_proceed_button);
        ImageButton cancelButton = mDialog.findViewById(R.id.submit_cancel_button);

        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();

        cancelButton.setOnClickListener((view -> {
            mDialog.cancel();
        }));

        yesButton.setOnClickListener(view -> {
            pushData();
        });

    }

    private void pushData(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<Void> pushFormRequest = apiInterface.submitForm("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse",
                mFirstName,
                mLastName,
                mEmailAddress,
                mGithubLink);

        pushFormRequest.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NotNull Call<Void> call,@NotNull Response<Void> response) {

                if(response.isSuccessful()){
                    showResponseDialog(R.drawable.ic_check_circle_black_24dp,R.string.submission_successful);
                    clearForm();
                    Toast.makeText(SubmitActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SubmitActivity.this,"Response Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<Void> call,@NotNull Throwable t) {
                showResponseDialog(R.drawable.ic_warning_black_24dp,R.string.submission_not_successful);
                Toast.makeText(SubmitActivity.this,"Failed due to: " + t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showResponseDialog(int imageRes, int textRes){
        mDialog.dismiss();

        mDialog = new Dialog(SubmitActivity.this);
        mDialog.setContentView(R.layout.dialog_response);
        ImageView imageView = mDialog.findViewById(R.id.response_image);
        TextView textView = mDialog.findViewById(R.id.response_text);

        imageView.setImageResource(imageRes);
        textView.setText(textRes);

        mDialog.setCanceledOnTouchOutside(true);
        mDialog.show();
    }

    private boolean verifyAllInputs(){
        boolean isValid = false;

        mFirstName = mFirstNameEdit.getText().toString();
        mLastName = mLastNameEdit.getText().toString();
        mGithubLink = mGithubLinkEdit.getText().toString();
        mEmailAddress = mEmailAddressEdit.getText().toString();

        if (mFirstName.trim().isEmpty()) {
            mFirstNameEdit.requestFocus();
            mFirstNameEdit.setError("First Name Required!");
        } else if (mLastName.trim().isEmpty()) {
            mLastNameEdit.requestFocus();
            mLastNameEdit.setError("Last Name Required!");
        } else if (mEmailAddress.trim().isEmpty()) {
            mEmailAddressEdit.requestFocus();
            mEmailAddressEdit.setError("Email Required!");
        } else if (mGithubLink.trim().isEmpty()) {
            mGithubLinkEdit.requestFocus();
            mGithubLinkEdit.setError("Project Link Required!");
        } else {
            isValid = true;
        }

        return isValid;
    }

    private void clearForm(){
        mFirstNameEdit.setText("");
        mLastNameEdit.setText("");
        mEmailAddressEdit.setText("");
        mGithubLinkEdit.setText("");
    }
}
