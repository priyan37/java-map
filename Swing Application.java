import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MapViewer {

    private static final String API_KEY = "YOUR_API_KEY_HERE";
    private static final String MAPS_API_URL = "https://maps.googleapis.com/maps/api/staticmap";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Google Maps Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 600);

            try {
                ImageIcon mapImage = new ImageIcon(fetchMapImage());
                JLabel label = new JLabel(mapImage);
                frame.add(label);
            } catch (IOException e) {
                e.printStackTrace();
            }

            frame.setVisible(true);
        });
    }

    private static Image fetchMapImage() throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = String.format("%s?center=37.7749,-122.4194&zoom=14&size=600x600&key=%s",
                MAPS_API_URL, API_KEY);

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            try (InputStream in = response.body().byteStream()) {
                return new ImageIcon(ImageIO.read(in)).getImage();
            }
        }
    }
}
