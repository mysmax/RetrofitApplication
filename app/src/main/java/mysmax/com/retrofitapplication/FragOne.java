package mysmax.com.retrofitapplication;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import mysmax.com.retrofitapplication.eventbus.BottomLayoutEvent;
import mysmax.com.retrofitapplication.eventbus.Frag1ClickEvent;
import mysmax.com.retrofitapplication.slider.SlidingUpPanelLayout;

public class FragOne extends Fragment implements OnMapReadyCallback, LoaderManager.LoaderCallbacks<String> {
     RelativeLayout parent;
     TextView tv1;
     GoogleMap googleMap;
     private SlidingDrawer drawer;
     SlidingUpPanelLayout slideUp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View fone = inflater.inflate(R.layout.fragment_one,container,false);
       parent = fone.findViewById(R.id.parent);
        fone.findViewById(R.id.btnfo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((TextView) fone.findViewById(R.id.tv1)).setText("F1 Btn 1");
               //EventBus eventBus = EventBus.getDefault();
               //Frag1ClickEvent object = new Frag1ClickEvent();
               //eventBus.post(object.setMsg1(""));
            }
        });
        fone.findViewById(R.id.btnfo2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((TextView) fone.findViewById(R.id.tv1)).setText("F1 Btn 2");
            }
        });
        ((TextView) fone.findViewById(R.id.tv1)).setText("");
        tv1 = fone.findViewById(R.id.tv1);

        final Fragment nested = new FragTwo();
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction trn = fm.beginTransaction();
        trn.replace(R.id.bottom_pane,nested).commitAllowingStateLoss();
        /**/
         // loader creation
         //getActivity().getSupportLoaderManager().initLoader(0,null,(LoaderManager.LoaderCallbacks<String>)this).forceLoad();
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button click  = fone.findViewById(R.id.btnClick);
        click.setVisibility(View.GONE);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //createDynamicLayout();
               // drawer.toggle();
                //((FrameLayout) fone.findViewById(R.id.bottom_pane_parent)).setVisibility(View.VISIBLE);
                /*
                SlidingUpPanelLayout.PanelState state = slideUp.getPanelState();
                if (SlidingUpPanelLayout.PanelState.COLLAPSED == state)
                {
                    slideUp.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                } else
                {
                    slideUp.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                }
                */

            }
        });
        //createSlider();
        //createBottomSlider();
       fone.findViewById(R.id.bottom_layout).setVisibility(View.GONE);
       slideUp = fone.findViewById(R.id.sliding_layout);
       slideUp.setPanelHeight(150);

        return fone;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void setMessage(Frag1ClickEvent event)
    {
        tv1.setText("" + event.msg2);
    }

    @Subscribe
    public void onEvent(BottomLayoutEvent event)
    {
        if (event != null)
        {
            if (event.isExpanded)
            {

                SlidingUpPanelLayout.PanelState state = slideUp.getPanelState();
                if (SlidingUpPanelLayout.PanelState.COLLAPSED == state)
                {
                    slideUp.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                } else
                {
                    slideUp.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                }
                /**/
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap1) {
        this.googleMap  = googleMap1;
        LatLng bangalore = new LatLng(12.914765, 77.586770);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bangalore,11));
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.setMyLocationEnabled(true);
        googleMap.setTrafficEnabled(true);
    }

    private void createDynamicLayout()
    {
        // Custom View
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View child = inflater.inflate(R.layout.layout_child_item,null);
        RelativeLayout.LayoutParams position = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        position.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        parent.addView(child,position);

        Button notNow = child.findViewById(R.id.btn_notnow);
        notNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getBaseContext(),"NOT NOW Click",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createSlider()
    {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View child = inflater.inflate(R.layout.layout_slider,null);
        drawer = child.findViewById(R.id.sliding_drawer);
        parent.addView(child);
    }

    private void createBottomSlider()
    {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View child = inflater.inflate(R.layout.layout_up_slide,null);
        parent.addView(child);
    }


    // start Loader call back
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {

        return new LoaderFetch(getActivity().getBaseContext());
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.e("Loader Response", "onLoadFinished Result : " + data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
    // end Loader call back

    class LoaderFetch extends AsyncTaskLoader<String>
    {

        public LoaderFetch(Context context) {
            super(context);
        }

        @Override
        public String loadInBackground() {
            String result = null;
            try {
                // URL
                URL url = new URL("http://api.github.com");
                // URL Connection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000); // check for server connection
                connection.connect();
                // response Reader
                Log.e("Loader Response", "loadInBackground Result : " + connection.getContentType() + " " + connection.getRequestMethod() + " " + connection.getContentLength());
                result = connection.getResponseMessage();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        public void deliverResult(String data) {
            super.deliverResult(data);
        }

        @Override
        public void deliverCancellation() {
            super.deliverCancellation();
        }
    }


}
