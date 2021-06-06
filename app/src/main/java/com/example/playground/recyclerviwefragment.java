package com.example.playground;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link recyclerviwefragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class recyclerviwefragment extends Fragment {
    EditText input;
    private View groundView;
    private RecyclerView mygroundlist;
    private DatabaseReference groundref, usersref;
    FirebaseRecyclerOptions<grounds> Roptions;
    FirebaseRecyclerAdapter<grounds,myviewholder> Radapter;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public recyclerviwefragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment recyclerviwefragment.
     */
    // TODO: Rename and change types and number of parameters
    public static recyclerviwefragment newInstance(String param1, String param2) {
        recyclerviwefragment fragment = new recyclerviwefragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        groundView = inflater.inflate(R.layout.fragment_recyclerviwefragment, container, false);
        mygroundlist = (RecyclerView) groundView.findViewById(R.id.ground_list);
        mygroundlist.setLayoutManager(new LinearLayoutManager(getContext()));
        groundref = FirebaseDatabase.getInstance().getReference().child("new ground");
        usersref = FirebaseDatabase.getInstance().getReference().child("new ground");

        input=groundView.findViewById(R.id.SC);
        //////////////////////////////////////////////
        Loaddata("");

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString()!=null)
                {
                    Loaddata(s.toString());
                }else {
                    Loaddata("");


                }

            }
        });
        return groundView;
    }

    private void Loaddata(String data) {
        Query query = usersref.orderByChild("location").startAt(data).endAt(data+"\uf8ff");
        Roptions=new FirebaseRecyclerOptions.Builder<grounds>().setQuery(query,grounds.class).build();
        Radapter= new FirebaseRecyclerAdapter<grounds, myviewholder>(Roptions) {
            @Override
            protected void onBindViewHolder(@NonNull myviewholder holder, int i, @NonNull grounds model) {
                holder.Rlocation.setText(model.getLocation());
                holder.Rgroundname.setText(model.getgroundname());
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // String cc = getRef(i).getKey();
                        Toast.makeText( getContext(), "kff", Toast.LENGTH_SHORT).show();
                        //Intent intent =new Intent(getActivity(),profileActivity.class);
                        //intent.putExtra("cc",cc);
                        //startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_display_layout,parent,false);
                return new myviewholder(v);
            }
        };
        Radapter.startListening();
        mygroundlist.setAdapter(Radapter);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<grounds>()
                        .setQuery(groundref, grounds.class)
                        .build();

        FirebaseRecyclerAdapter<grounds, groundsviewholder> adapter
                = new FirebaseRecyclerAdapter<grounds, groundsviewholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull groundsviewholder groundsviewholder, int i, @NonNull grounds grounds) {


                groundsviewholder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      //    Toast.makeText( getContext(), "k", Toast.LENGTH_SHORT).show();
                        String s = getRef(i).getKey();
                        Intent pintent = new Intent(getActivity(),profileActivity.class );
                        pintent.putExtra("s", s);
                        startActivity(pintent);


       /* String visite_ground_details=getRef(i).getKey();

        Intent profilintent=new Intent(getActivity(),profileActivity.class);
        profilintent.putExtra("visite_ground_details",visite_ground_details);
        startActivity(profilintent);
    //startActivity(new Intent(getActivity(),profileActivity.class));
  */
                    }
                });


                String ground_id = getRef(i).getKey();
                usersref.child(ground_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.hasChild("groundName")) {
                            String Gname = snapshot.child("groundName").getValue().toString();
                            String Locname = snapshot.child("location").getValue().toString();

                            //////////////////////////////////////////////////////////////////////////////////////

                            String image=snapshot.child("image").getValue().toString();





                            groundsviewholder.groundsname.setText(Gname);
                            groundsviewholder.locations.setText(Locname);

                            Picasso.get().load(image).placeholder(R.drawable.add).into(groundsviewholder.profimage);
                        }
                        else {
                            String Gname = snapshot.child("groundName").getValue().toString();
                            String Locname = snapshot.child("location").getValue().toString();

                            //////////////////////////////////////////////////////////////////////////////////////







                            groundsviewholder.groundsname.setText(Gname);
                            groundsviewholder.locations.setText(Locname);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @NonNull
            @Override
            public groundsviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_display_layout, parent, false);
                groundsviewholder viewholder = new groundsviewholder(view);
                return viewholder;
            }
        };
        mygroundlist.setAdapter(adapter);
        adapter.startListening();

    }

    public static class groundsviewholder extends RecyclerView.ViewHolder {
        TextView groundsname, locations;
        ImageView profimage;

        public groundsviewholder(@NonNull View itemView) {
            super(itemView);

            groundsname = itemView.findViewById(R.id.ground_name);
            locations = itemView.findViewById(R.id.location_name);
            profimage=itemView.findViewById(R.id.my_image);
        }
    }

}