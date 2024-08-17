import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class GoogleMapsClient {

    private static final String API_KEY = "YOUR_API_KEY_HERE";
    private static final String MAPS_API_URL = "https://maps.googleapis.com/maps/api/staticmap";

    public static void main(String[] args) {
        try {
            OkHttpClient client = new OkHttpClient();

            String url = String.format("%s?center=37.7749,-122.4194&zoom=14&size=400x400&key=%s",
                    MAPS_API_URL, API_KEY);

            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                // Save the response body to a file or process it as needed
                System.out.println(response.body().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

