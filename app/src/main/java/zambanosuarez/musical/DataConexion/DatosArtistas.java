package zambanosuarez.musical.DataConexion;


import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zambanosuarez.musical.R;

public class DatosArtistas  extends ArrayAdapter {
//las url de donde obtendremos los datos del servidor
    private static final String URL_BASE = "http://168.62.42.172/ciudadmusical/obtenerartistas/";
    private static final String URL_IMG = "http://168.62.42.172/ciudadmusical/public/";
    private static final String URL_AUDIO = "http://168.62.42.172/ciudadmusical/public/";

    private static final String TAG = "DatosGeneros";
    //creamos variables JSARRAY
    JsonArrayRequest jsArrayRequest;

    ArrayList<DatosCA> items;
    Context context;
    LayoutInflater layoutInflater;
    private RequestQueue requestQueue;
    private ProgressBar progressBar;
    String a;
    private String imag;

    public DatosArtistas(Context context, String tab, final View View22) {
        super(context, 0);
        requestQueue = Volley.newRequestQueue(context);
        this.a=tab;
        //Se inicia Una instancia Tipo Get para obtener los datos del server
        jsArrayRequest = new JsonArrayRequest(Request.Method.GET,URL_BASE+tab,(String) null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONArray response) {
                        //Parseamos La data a una arrayLista con la funcion ParseJson
                        items = (ArrayList<DatosCA>) parseJson(response);
                        notifyDataSetChanged();
                        Log.d(TAG, URL_BASE+a+"");

                        //findViewById(R.id.progressBar1).setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                    }
                }
        );
        requestQueue.add(jsArrayRequest);

    }
    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //inflamos el layout para poder enviar los datosa las vistas

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Referencia del view procesado
        View listItemView;

        DatosCA item = items.get(position);

        //Comprobando si el View no existe
        listItemView = null == convertView ? layoutInflater.inflate(R.layout.artistas,parent,false) : convertView;
        TextView textoTitulo =listItemView.findViewById(R.id.textoTitulo);
        TextView url=listItemView.findViewById(R.id.urlcancion);
        TextView textDescripcion =listItemView.findViewById(R.id.textDescripcion);
        TextView Estado =listItemView.findViewById(R.id.estado);
        textoTitulo.setText(item.getNombre());

        textDescripcion.setText(item.getMail());
        Estado.setText(item.getTipo());
        final ImageView imagenPost = (ImageView) listItemView.findViewById(R.id.imagenPost);
        //imag=item.getImagen_cancion();
        //hacemos una instacia para cargar las imagenes
        ImageRequest request = new ImageRequest(
                URL_IMG+item.getArchivo_img(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imagenPost.setImageBitmap(bitmap);
                        //  Log.d(TAG, "Error en respuesta Bitmap: " +URL_IMG+imag);
                    }
                }, 0, 0, null, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imagenPost.setImageResource(R.drawable.artistas);
                        Log.d(TAG, "Error en respuesta Bitmap: " + error.getMessage());
                    }
                });
        requestQueue.add(request);
        return listItemView;
    }


    public List<DatosCA> parseJson(JSONArray jsonObject) {
        // Variables locales
        List<DatosCA> posts = new ArrayList<>();


        // Obtener el array del objeto


        for (int i = 0; i < jsonObject.length(); i++) {

            try {
                JSONObject objeto = jsonObject.getJSONObject(i);
//Enviamos los datos a la clase DatosCA
                DatosCA post = new DatosCA(
                        objeto.getInt("id"),
                        objeto.getString("nombre"),
                        objeto.getString("username"),
                        objeto.getString("mail"),
                        objeto.getString("password"),
                        objeto.getString("created_at"),
                        objeto.getString("deleted_at"),
                        objeto.getString("updated_at"),
                        objeto.getString("tipo_usuario_id"),
                        objeto.getString("remember_token"),
                        objeto.getString("archivo_img"),
                        objeto.getString("tipo"),
                        objeto.getString("descripcion"));

                //Log.e(TAG, ""+objeto.getString("genero"));

                posts.add(post);

            } catch (JSONException e) {
                Log.e(TAG, "Error de parsing: " + e.getMessage());
            }
        }

        //item2= (ArrayList<Post>) posts;
        return posts;
    }

}
