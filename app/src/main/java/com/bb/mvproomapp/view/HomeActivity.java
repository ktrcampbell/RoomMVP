package com.bb.mvproomapp.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.room.Room;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bb.mvproomapp.R;
import com.bb.mvproomapp.database.UserEntity;
import com.bb.mvproomapp.database.UsersDB;
import com.bb.mvproomapp.presenter.Contract;
import com.bb.mvproomapp.presenter.RoomPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity implements Contract.View {

    //private UsersDB usersDB;

    private Contract.RoomPresenter mainPresenter;

    @BindView(R.id.username_edittext)
    EditText usernameEditText;

    @BindView(R.id.password_edittext)
    EditText passwordEditText;

    private ProfileFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new RoomPresenter(this);

        fragment = new ProfileFragment();


        //this block now is being done by Presenter
//        usersDB = Room.databaseBuilder(
//                this,
//                UsersDB.class,
//                "users.db").allowMainThreadQueries()
//                .build();
////        usersDB.getUserDAO().addNewUser(new UserEntity("Lovey", "puppyToes"));
//
//        UserEntity userEntity = usersDB.getUserDAO().loginSelect("Lovey", "puppyToes");
//
//        if(userEntity == null){
//            Log.d("TAG_K", "Results: " + userEntity.getUserName());
//        }else {
//            Log.d("TAG_K", "Login success");

            // to update a value in the database
            //usersDB.getUserDAO().updateValue();
//        }
    }

    @OnClick(R.id.login_button)
    public void loginClick(View view){
        String name = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        mainPresenter.logInUser(name, password);
    }

    @Override
    public void userLoginSuccess() {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_frame, fragment)//use so that fragment will be updated with new info on each call
                .commit();
    }

    public String getUsername(){
        return mainPresenter.getUserInstance().getUserName();
    }

    public void logout(){
        mainPresenter.signOutUser();
    }

    @Override
    public void userLoginFailed() {

        new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme))
                .setTitle("Login failed")
                .setMessage("Username or password is invalid.  Please re-enter.")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                })
                .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        usernameEditText.setText("");
                        passwordEditText.setText("");
                    }
                })
                .create()
                .show();
    }

    @Override
    public void userLoggedOut() {

        getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commit();
    }

    @Override
    public void userNotLoggedInMessage() {

    }

    @Override
    public void displayResults() {

    }
}
