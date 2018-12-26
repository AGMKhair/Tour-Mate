package com.xcoders.tourmate.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.xcoders.tourmate.Model.Event;
import com.xcoders.tourmate.R;
import com.xcoders.tourmate.Variable.Variable;

import static com.xcoders.tourmate.Variable.Variable.myRef;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {


    private TextView nameTV,toDateTV,formDateTV,budgetTV;
    private CardView cardView;
    private View view;


    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setTitle("Events");
        //database = FirebaseDatabase.getInstance();
        //myRef = database.getReference("Event");
       view = inflater.inflate(R.layout.fragment_event, container, false);
       nameTV = view.findViewById(R.id.destinationNameIdFM);
        //nameTV.setText("dsfa");
       toDateTV = view.findViewById(R.id.toDateIdFM);
       formDateTV = view.findViewById(R.id.formDateIdFM);
       budgetTV = view.findViewById(R.id.budgetIdFM);

       cardView = view.findViewById(R.id.card);

       cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {
               FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
               ft.replace(R.id.frameLayaoutID,new EventDetailsFragment());
               ft.commit();
           }
       });

        FloatingActionButton floatingActionButton = view
                .findViewById(R.id.floating_action_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click.

                LinearLayout layoutBottomSheet;
                final TextView btnBottomSheet = view.findViewById(R.id.btnBottomSheet);
                BottomSheetBehavior sheetBehavior;

                View view1 = getLayoutInflater().inflate(R.layout.add_event_dialog, null);

                BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                dialog.setContentView(view1);
                dialog.show();

                final EditText eventNameET,eventToDateET,eventFromDateET,eventBudgetET;

                eventNameET = view1.findViewById(R.id.eventNameId);
                eventToDateET = view1.findViewById(R.id.toDateId);
                eventFromDateET = view1.findViewById(R.id.formDateId);
                eventBudgetET = view1.findViewById(R.id.budgetId);

                Button button = view1.findViewById(R.id.submitId);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name , toDate,fromDate,budget;
                        name = eventNameET.getText().toString();
                        toDate = eventToDateET.getText().toString();
                        fromDate = eventFromDateET.getText().toString();
                        budget = eventBudgetET.getText().toString();
                        if(name.isEmpty() || toDate.isEmpty() || fromDate.isEmpty() || budget.isEmpty())
                        {
                            Toast.makeText(getActivity(), "All field required", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String eventid = String.valueOf(myRef.push().getKey());
                            //Toast.makeText(getActivity(), eventid, Toast.LENGTH_SHORT).show();
                            Event event =new Event(eventid,name,toDate,fromDate,budget);
                            myRef.child(eventid).setValue(event);

                            Toast.makeText(getActivity(), "Event Saved", Toast.LENGTH_SHORT).show();
                            Datashow();
                        }
                    }
                });


            }
        });


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Datashow();
    }

    private void Datashow()
    {
        //nameTV.setText("dataSnapshot.getChildren().toString()");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Event event1 = dataSnapshot1.getValue(Event.class);
                    nameTV.setText(event1.getName());
                    toDateTV.setText(event1.getTodate());
                    formDateTV.setText(event1.getFormdate());
                    budgetTV.setText(event1.getBudget());
                    Variable.event_id = event1.getEventid();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //login


    @Override
    public void onStart() {
        super.onStart();
        Datashow();
    }
}
