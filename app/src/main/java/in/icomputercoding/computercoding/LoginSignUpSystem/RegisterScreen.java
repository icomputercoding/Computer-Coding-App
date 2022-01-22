package in.icomputercoding.computercoding.LoginSignUpSystem;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

import in.icomputercoding.computercoding.R;
import in.icomputercoding.computercoding.WelcomeScreen;
import in.icomputercoding.computercoding.databinding.RegisterScreenBinding;

public class RegisterScreen extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference reference;
    String name, email, pass;
    private long pressedTime;
    RegisterScreenBinding binding;

    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            Intent i = new Intent(this, WelcomeScreen.class);
            startActivity(i);
            super.onBackPressed();
            finish();
        }
        pressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RegisterScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.loading_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        binding.arrowBack.setOnClickListener(view -> {
            startActivity(new Intent(RegisterScreen.this,WelcomeScreen.class));
            finish();
        });

        binding.login.setOnClickListener(view -> {
            startActivity(new Intent(RegisterScreen.this, LoginScreen.class));
            finish();
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = binding.etName.getText().toString();
                email = binding.etEmail.getText().toString();
                pass = binding.etPass.getText().toString();
                validateUser();

            }

            private void validateUser() {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String checkPassword = "^" +
                        //"(?=.*[0-9])" +         //at least 1 digit
                        //"(?=.*[a-z])" +         //at least 1 lower case letter
                        //"(?=.*[A-Z])" +         //at least 1 upper case letter
                        "(?=.*[a-zA-Z])" +      //any letter
                        //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                        "(?=S+$)" +           //no white spaces
                        ".{4,}" +               //at least 4 characters
                        "$";
                if (email.isEmpty() || name.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(RegisterScreen.this,"All fields are required",Toast.LENGTH_SHORT).show();
                } else if (!email.matches(emailPattern)) {
                    Toast.makeText(RegisterScreen.this,"Enter a  valid email address",Toast.LENGTH_SHORT).show();
                } else if (!pass.matches(checkPassword)) {
                    Toast.makeText(RegisterScreen.this,"Password should contain 4 characters!",Toast.LENGTH_SHORT).show();
                } else  {
                    registerUser();
                }

            }

            private void registerUser() {

                dialog.show();
                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        FirebaseUser user = auth.getCurrentUser();
                        assert user != null;
                        String userId = user.getUid();
                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("userId", userId);
                        hashMap.put("name", name);
                        hashMap.put("email", email);
                        reference.setValue(hashMap).addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                Intent i = new Intent(RegisterScreen.this, LoginScreen.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                            } else {
                                Toast.makeText(RegisterScreen.this, Objects.requireNonNull(task1.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


                    } else  {

                        dialog.hide();
                        Toast.makeText(RegisterScreen.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


}