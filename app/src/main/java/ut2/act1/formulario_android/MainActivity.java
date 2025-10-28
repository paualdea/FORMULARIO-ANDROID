package ut2.act1.formulario_android;

// IMPORTACIONES
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
// Estas son las clases de los elementos de la actividad principal
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

/**
 * Clase principal de la actividad.
 *
 * @author Pau Aldea Batista
 * @version 1.0
 * @since 20-10-2025
 */
public class MainActivity extends AppCompatActivity {
    // Creamos los elementos de la actividad
    private EditText nombre, email, password;
    private Button button;

    /**
     * Función que se ejecuta al crear la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Conectamos los elementos de la actividad con el código
        nombre = findViewById(R.id.nombre);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        button = findViewById(R.id.button);

        // Añadimos un listener al botón
        button.setOnClickListener(v -> {
            // Ejecutamos la función que comprueba que los campos esten completados
            comprobarCamposRellenados(nombre, email, password);
        });
    }

    /**
     * Función que comprueba que los campos esten completados.
     * Pasa como parámetros los EditText de la actividad principal.
     *
     * @param nombre
     * @param email
     * @param password
     */
    private void comprobarCamposRellenados(EditText nombre, EditText email, EditText password) {
        // Variable booleana para recibir si los campos son válidos o no.
        boolean validos = false;

        // Valores de los campos obtenidos y convertidos a String
        String nombreTexto = nombre.getText().toString();
        String emailTexto = email.getText().toString();
        String passwordTexto = password.getText().toString();

        // Mensaje de error
        String error = "Hay algún campo vacío";

        // Comprobamos con un if que los campos no esten vacios.
        if (nombreTexto.isEmpty() || emailTexto.isEmpty() || passwordTexto.isEmpty()) {
            // Ejecutamos la función que muestra errores en pantalla.
            mostrarError(error);
        }
        // En caso contrario, ejecutamos la función que comprueba si los campos estan bien rellenados
        else {
            // Llamamos a la función para establecer el valor de la variable validos.
            validos = comprobarCamposCorrectos(emailTexto, passwordTexto);

            // Si el valor de validos es true entonces lanzar una ventana emergente de registro exitoso.
            if (validos) {
                registroExitoso(nombreTexto);
            }
        }
    }

    /**
     * Esta función comprueba que los campos esten rellenados con valores correctos
     * Pasa como parámetros el contenido en String de los EditText de la actividad.
     *
     * @param email
     * @param password
     */
    private boolean comprobarCamposCorrectos (String email, String password) {
        String error = "";

        // Comprobación del campo email usando el patrón EMAIL_ADDRESS incluido en Android Studio.
        if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            error = "El email no es válido";

            // Lanzamos la ventana de error llamando a la función mostrarError().
            mostrarError(error);

            // Mandamos un false ya que no se cumplen todas las condiciones
            return false;
        }

        // Comprobación de la longitud minima y la complejidad de la contraseña.
        if (password.length() < 8) {
            error = "La contraseña debe tener 8 caracteres mínimo";

            // Lanzamos la ventana de error llamando a la función mostrarError().
            mostrarError(error);

            // Mandamos un false ya que no se cumplen todas las condiciones
            return false;
        }

        // Si el código llega hasta aquí, significa que todos los campos son válidos, así que devolvemos un true.
        return true;
    }

    /**
     * Función que muestra errores en pantalla.
     * Recibe como parámetro un String con el error que debe mostrarse en pantalla, dependiendo del caso.
     *
     * @param error
     */
    private void mostrarError (String error) {
        // Creamos una ventana de error (AlertDialog) que muestra el error pasado por la variable error
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(error)
                .setPositiveButton("Entendido", null)
                .show();
    }

    /**
     * Esta función tiene la única misión de mostrar un Toast con el mensaje de registro exitoso.
     */
    private void registroExitoso(String nombre) {
        // Creamos un Toast para mostrar el registro exitoso del usuario X.
        Toast.makeText(getApplicationContext(), "Usuario " + nombre + " registrado correctamente", Toast.LENGTH_LONG).show();
    }
}