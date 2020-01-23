package id.web.runsel.udaraq.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import id.web.runsel.udaraq.R;
import id.web.runsel.udaraq.activity.auth.LoginActivity;
import id.web.runsel.udaraq.response.ProfileResponse;
import id.web.runsel.udaraq.response.UserResponse;
import id.web.runsel.udaraq.model.User;
import id.web.runsel.udaraq.service.ProfileService;
import id.web.runsel.udaraq.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView pageTitle, subTitle, unSubTitle;
    private CardView cardheader;
    private EditText editName, editPassword, editRepassowrd;
    private CircularProgressButton saveButton, logoutButton;
    private String name, email;
    private CircularProgressButton btn;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

        if(!LoginActivity.appPreference.getLoginStatus()) {
            Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
            getActivity().finish();
            startActivity(intent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        cardheader = view.findViewById(R.id.headerCard);
        cardheader.setCardBackgroundColor(Color.parseColor("#FD955C"));
        pageTitle = view.findViewById(R.id.headerTitle);
        pageTitle.setText("PROFILE");
        subTitle = view.findViewById(R.id.subTitleHeader);
        subTitle.setText("");
        unSubTitle = view.findViewById(R.id.unSubTitleHeader);
        unSubTitle.setText("");

        editName = view.findViewById(R.id.editTextName);
        editPassword = view.findViewById(R.id.editTextPassword);
        editRepassowrd = view.findViewById(R.id.editTextRePassword);
        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save(v);
            }
        });

        logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout(v);
            }
        });

        ProfileService api = RetrofitInstance.getClient().create(ProfileService.class);
        Call<ProfileResponse> call = api.getProfile("Bearer "+ LoginActivity.appPreference.getToken());
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.isSuccessful()) {
                    try{
                        subTitle.setText(response.body().getName());
                        unSubTitle.setText(response.body().getEmail());
                        editName.setText(response.body().getName());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                } else {
                    //Log.e("response-failure", call.toString());
                    //Toast.makeText(getApplicationContext(), "Authentication failed!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            /*throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void save(View view) {
        btn = view.findViewById(R.id.saveButton);
        btn.startAnimation();

        final String name = editName.getText().toString();
        String password = editPassword.getText().toString();
        String repassword = editRepassowrd.getText().toString();

        User user = new User(name, password, repassword);

        ProfileService api = RetrofitInstance.getClient().create(ProfileService.class);
        Call<UserResponse> call = api.updateProfile("Bearer "+ LoginActivity.appPreference.getToken(), user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()) {
                    btn.revertAnimation();
                    subTitle.setText(name);

                    Toast.makeText(getActivity().getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    btn.revertAnimation();
                    Toast.makeText(getActivity().getApplicationContext(), "Check your input", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                btn.revertAnimation();
                Log.e("response-failure", call.toString());
                Toast.makeText(getActivity().getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void logout(View view) {
        btn = view.findViewById(R.id.logoutButton);
        btn.startAnimation();

        LoginActivity.appPreference.clearData();
        btn.revertAnimation();
        Intent intent = new Intent(getActivity().getApplicationContext(), LoginActivity.class);
        getActivity().finish();
        startActivity(intent);
    }
}
