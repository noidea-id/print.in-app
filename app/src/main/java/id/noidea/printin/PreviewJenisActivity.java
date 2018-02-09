package id.noidea.printin;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

public class PreviewJenisActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout mDemoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_jenis);

        mDemoSlider = findViewById(R.id.slider);


        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("1",R.drawable.slide_1);
        file_maps.put("2",R.drawable.slide_2);
        file_maps.put("3",R.drawable.slide_3);

        for(String name : file_maps.keySet()){
            DefaultSliderView sliderView = new DefaultSliderView(getBaseContext());
            // initialize a SliderLayout
            sliderView
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            mDemoSlider.addSlider(sliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setDuration(3000);
//        mDemoSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        mDemoSlider.addOnPageChangeListener(this);
        mDemoSlider.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
    }

    @Override
    public void onStop() {
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }
    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getBaseContext(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
