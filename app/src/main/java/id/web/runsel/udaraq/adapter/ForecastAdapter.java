package id.web.runsel.udaraq.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;
import id.web.runsel.udaraq.R;
import id.web.runsel.udaraq.response.DataForecast;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.MyViewHolder> {
    List<DataForecast> forecastList;

    public ForecastAdapter(List <DataForecast> ForecastList) {
        forecastList = ForecastList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_list, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.day.setText(forecastList.get(position).getDay());
        holder.temp.setText(forecastList.get(position).getTemp().toString()+"\u00B0C");
        holder.predict.setText(forecastList.get(position).getPredict());
        String url_icon = "https://darksky.net/images/weather-icons/"+forecastList.get(position).getIcon();
        Picasso.get().load("https://darksky.net/images/weather-icons/"+forecastList.get(position).getIcon()).fit().into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView day, predict, temp;
        public ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.forecastDay);
            icon = itemView.findViewById(R.id.forecastIcon);
            predict = itemView.findViewById(R.id.forecastPredict);
            temp = itemView.findViewById(R.id.forecastTemp);
        }
    }
}
