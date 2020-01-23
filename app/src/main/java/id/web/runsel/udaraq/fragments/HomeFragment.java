package id.web.runsel.udaraq.fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import id.web.runsel.udaraq.R;
import id.web.runsel.udaraq.activity.DashboardActivity;
import id.web.runsel.udaraq.activity.auth.LoginActivity;
import id.web.runsel.udaraq.response.WeatherResponse;
import id.web.runsel.udaraq.service.RetrofitInstance;
import id.web.runsel.udaraq.service.WeatherService;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView pageTitle, subTitle, unSubTitle, aqi, pm, vis, hum, aqiStatus, temp, temp_desc, wind, precip, pressur;
    private CardView aqiStatusCard, hourlyCard;
    private ImageView iconTemp;
    private String lt, ln;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        //return inflater.inflate(R.layout.fragment_home, container, false);
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ((DashboardActivity) getActivity()).getLastLocation();
        pageTitle = view.findViewById(R.id.headerTitle);
        pageTitle.setText("HOME");

        subTitle = view.findViewById(R.id.subTitleHeader);
        unSubTitle = view.findViewById(R.id.unSubTitleHeader);

        aqi = view.findViewById(R.id.aqiHeaderText);
        vis = view.findViewById(R.id.particleHeaderText);
        hum = view.findViewById(R.id.humidityHeaderText);

        aqiStatus = view.findViewById(R.id.aqiStatusText);
        aqiStatusCard = view.findViewById(R.id.aqiStatusCard);
        temp = view.findViewById(R.id.tempStatusHome);
        iconTemp = view.findViewById(R.id.iconTempHome);
        temp_desc = view.findViewById(R.id.tempHeaderDesc);
        wind = view.findViewById(R.id.windHomeText);
        precip = view.findViewById(R.id.precipHomeText);
        precip.setVisibility(View.GONE);
        pressur = view.findViewById(R.id.pressureHomeText);

        hourlyCard = view.findViewById(R.id.hourlyCard);
        hourlyCard.setVisibility(View.GONE);

        updateStatus();

        return view;
    }

    private void updateStatus() {
        String url = "terdekat/"+LoginActivity.appPreference.getLat()+"/"+LoginActivity.appPreference.getLon();

        WeatherService api = RetrofitInstance.getClient().create(WeatherService.class);
        Call<WeatherResponse> call = api.getCurrentLocation("Bearer "+ LoginActivity.appPreference.getToken(), url);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if(response.isSuccessful()) {
                    try{
                        subTitle.setText(response.body().getCity());
                        unSubTitle.setText(response.body().getProvince());

                        aqi.setText(response.body().getAirQuality().toString());
                        vis.setText(response.body().getVisibility()+"+ km");
                        Double humi = response.body().getHumidity() * 100;
                        hum.setText(humi.toString()+"%");

                        aqiStatus.setText(response.body().getAirStatus());
                        aqiStatusCard.setCardBackgroundColor(Color.parseColor(response.body().getAirColor()));

                        String url_icon = "https://darksky.net/images/weather-icons/"+response.body().getIcon();
                        Picasso.get().load(url_icon).into(iconTemp);
                        temp.setText(response.body().getTemperature().toString()+"\u00B0C");
                        temp_desc.setText(response.body().getSummary());
                        wind.setText("Wind : "+response.body().getWindSpeed().toString()+" m/h");
                        pressur.setText("Pressure : "+response.body().getPressure().toString()+" mb");

                    }catch (Exception e){

                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                updateStatus();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
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
}
