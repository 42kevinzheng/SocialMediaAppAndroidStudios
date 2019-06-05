package com.example.a13811.socialmedianiddle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private TextView userName, userProfileName, userStatus, userCountry, userGender, userRelationship, userDOB;
    private CircleImageView userProfileImage;

    private DatabaseReference profileUserRef;
    private FirebaseAuth mAuth;
    private String currentUserID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        mAuth=FirebaseAuth.getInstance();
        currentUserID = mAuth.getCurrentUser().getUid();
        profileUserRef= FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID);




        userName=(TextView)findViewById(R.id.my_username);
        userProfileName=(TextView)findViewById(R.id.my_profile_full_name);
        userStatus=(TextView)findViewById(R.id.my_profile_status);
        userCountry=(TextView)findViewById(R.id.my_country);
        userGender=(TextView)findViewById(R.id.my_gender);
        userRelationship=(TextView)findViewById(R.id.my_relationship_status);
        userDOB=(TextView)findViewById(R.id.my_dob);


        userProfileImage=(CircleImageView)findViewById(R.id.my_profile_pic);


        profileUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    String myProfileImage= dataSnapshot.child("profileimage").getValue().toString();
                    String myUserName= dataSnapshot.child("username").getValue().toString();
                    String myProfileFullName= dataSnapshot.child("fullname").getValue().toString();
                    String myProfileStatus= dataSnapshot.child("status").getValue().toString();
                    String myDOB= dataSnapshot.child("dob").getValue().toString();
                    String myCountry= dataSnapshot.child("country").getValue().toString();
                    String myGender= dataSnapshot.child("gender").getValue().toString();
                    String myRelationshipStatus= dataSnapshot.child("relationshipstatus").getValue().toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }
}
