package ec.edu.espol.proyectoed1;

import java.io.IOException;

public class SecondaryController {

    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}