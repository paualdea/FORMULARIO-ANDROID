package ut2.act1.formulario_android;

// IMPORTACIONES
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
// Estas son las clases de los elementos de la actividad principal
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Button;

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

        // Mensaje de error
        String error = "Hay algún campo vacío";

        // Comprobamos con un if que los campos no esten vacios.
        if (nombre.toString().isEmpty() || email.toString().isEmpty() || password.toString().isEmpty()) {
            // Ejecutamos la función que muestra errores en pantalla.
            mostrarError(error);
        }
        // En caso contrario, ejecutamos la función que comprueba si los campos estan bien rellenados
        else {
            // Llamamos a la función para establecer el valor de la variable validos.
            validos = comprobarCamposCorrectos(email, password);

            // Si el valor de validos es true entonces lanzar una ventana emergente de registro exitoso.
            if (validos) {
                registroExitoso(nombre);
            }
        }
    }

    /**
     * Esta función comprueba que los campos esten rellenados con valores correctos
     * Pasa como parámetros los EditText de la actividad principal.
     *
     * @param email
     * @param password
     */
    private boolean comprobarCamposCorrectos (EditText email, EditText password) {
        String error = "";
        // Obtenemos los valores de los campos y los convertimos a String (obviando el nombre ya que no necesitamos comprobarlo).
        String emailText = email.toString().trim();
        String passwordText = password.toString().trim();

        // Comprobación del campo email usando el patrón EMAIL_ADDRESS incluido en Android Studio.
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            error = "El email no es válido";

            // Lanzamos la ventana de error llamando a la función mostrarError().
            mostrarError(error);

            // Mandamos un false ya que no se cumplen todas las condiciones
            return false;
        }

        // Comprobación de la longitud minima y la complejidad de la contraseña.
        if (passwordText.length() < 8) {
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
     * Esta función tiene la única misión de mostrar una ventana con el mensaje de registro exitoso.
     * Tras cerrar esta ventana emergente la aplicación se cierra.
     */
    private void registroExitoso(EditText nombre) {
        String nombreTexto = nombre.toString();

        // Creamos una nueva ventana emergente para mostrar el registro exitoso
        new AlertDialog.Builder(this)
                .setTitle("Registro completado")
                .setMessage("El usuario " + nombreTexto + " ha sido registrado correctamente")
                // Añadimos un listener al botón de la ventana emergente para que cuando se pulse se cierre la aplicación
                .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Esta línea cierra la aplicación al completo.
                        finishAffinity();
                    }
                })
                .show();
    }


}