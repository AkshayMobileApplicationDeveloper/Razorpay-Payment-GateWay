package com.app.razorpaypaymentgateway;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.skydoves.elasticviews.ElasticButton;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    ElasticButton ButtonPayment;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        /**
         * initialization
         */
        ButtonPayment = findViewById(R.id.button);

        ButtonPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Payment start", Toast.LENGTH_SHORT).show();
                startPayment();

            }

        });

        /**
         * Preload payment resources
         */
        Checkout.preload(getApplicationContext());
    }

    public void startPayment() {
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Set your key ID here
         */
        checkout.setKeyID("rzp_test_CBFvIVPLPqKrW4");

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.razorpay);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "Merchant Name");
            options.put("description", "Reference No. #123456");
            options.put("image", "http://example.com/image/rzp.jpg");
            options.put("order_id", "order_DBJOWzybf0sJbb"); // from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", "50000"); // pass amount in currency subunits
            options.put("prefill.email", "gaurav.kumar@example.com");
            options.put("prefill.contact", "9988776655");

            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            checkout.open(activity, options);

        } catch (Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        /**
         * Add your logic here for a successful payment response
         */
        Log.i(TAG, "Payment Successful: " + razorpayPaymentID);
    }

    @Override
    public void onPaymentError(int code, String response) {
        /**
         * Add your logic here for a failed payment response
         */
        Log.e(TAG, "Payment failed with error code " + code + " and response: " + response);
    }
}
