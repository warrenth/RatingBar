package pe.warrneth.ratingbarsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import pe.warrneth.ratingbar.RatingBar;

public class MainActivity extends AppCompatActivity {

    private EditText mEditStarMaxCount;
    private EditText mEditStarRateCount;
    private RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRatingBar = (RatingBar) findViewById(R.id.RatingBar1);
        mEditStarMaxCount = (EditText) findViewById(R.id.edit_starMaxCount);
        mEditStarRateCount = (EditText) findViewById(R.id.edit_starRateCount);
        findViewById(R.id.button_makeRatingBar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maxCount = mEditStarMaxCount.getText().toString().trim();
                String rateCount = mEditStarRateCount.getText().toString().trim();

                if(!TextUtils.isEmpty(rateCount) && !TextUtils.isEmpty(maxCount)) {
                    mRatingBar.setRatingMaxCount(Integer.parseInt(maxCount));
                    mRatingBar.setRatingCount(Float.parseFloat(rateCount));
                    mRatingBar.invalidateView();
                }
            }
        });
    }
}
