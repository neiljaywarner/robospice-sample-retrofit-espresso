package com.octo.android.robospice.sample.retrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.sample.retrofit.model.Contributor;
import com.octo.android.robospice.sample.retrofit.network.SampleRetrofitSpiceRequest;

/**
 * This sample demonstrates how to use RoboSpice to perform simple network requests.
 * @author sni
 */
public class SampleSpiceActivity extends BaseSampleSpiceActivity {

    // ============================================================================================
    // ATTRIBUTES
    // ============================================================================================

    private TextView mTextView;

    private SampleRetrofitSpiceRequest githubRequest;

    // ============================================================================================
    // ACTIVITY LIFE CYCLE
    // ============================================================================================

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.main);

        mTextView = (TextView) findViewById(R.id.textview_lorem_ipsum);

        githubRequest = new SampleRetrofitSpiceRequest("octo-online", "robospice");
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpiceManager().execute(githubRequest, "github", DurationInMillis.ONE_MINUTE, new ListContributorRequestListener());

            }
        });
        //TODO: Try out jack options and lambdas just for the 5 minute experience, although if it doesn't work with instant run not sure if we want to use it anytime soon.

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    // ============================================================================================
    // PRIVATE METHODS
    // ============================================================================================

    private void updateContributors(final Contributor.List result) {
        String originalText = getString(R.string.textview_text);

        StringBuilder builder = new StringBuilder();
        builder.append(originalText);
        builder.append('\n');
        builder.append('\n');
        for (Contributor contributor : result) {
            builder.append('\t');
            builder.append(contributor.login);
            builder.append('\t');
            builder.append('(');
            builder.append(contributor.contributions);
            builder.append(')');
            builder.append('\n');
        }
        mTextView.setText(builder.toString());
    }

    // ============================================================================================
    // INNER CLASSES
    // ============================================================================================

    public final class ListContributorRequestListener implements RequestListener<Contributor.List> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(SampleSpiceActivity.this, "failure", Toast.LENGTH_SHORT).show();
            Log.e("NJW","*** On Request FAilure" );
        }

        @Override
        public void onRequestSuccess(final Contributor.List result) {
            Toast.makeText(SampleSpiceActivity.this, "success", Toast.LENGTH_SHORT).show();
            Log.e("NJW","*** On Request Success" );

            updateContributors(result);
        }
    }
}
