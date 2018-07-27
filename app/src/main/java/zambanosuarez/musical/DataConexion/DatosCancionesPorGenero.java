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

public class DatosCancionesPorGenero extends ArrayAdapter {

    private static final String URL_BASE = "http://168.62.42.172/ciudadmusical/obtenercancionesporgenero/";
    private static final String URL_IMG = "http://168.62.42.172/ciudadmusical/public/";
    private static final String URL_AUDIO = "http://168.62.42.172/ciudadmusical/public/";

    private static final String TAG = "DatosGeneros";
    JsonArrayRequest jsArrayRequest;

    ArrayList<DatosCG> items;
    Context context;
    LayoutInflater layoutInflater;
    private RequestQueue requestQueue;
    private ProgressBar progressBar;
    String a;
    private String imag;

    public DatosCancionesPorGenero(Context context, String tab, final View View22) {
        super(context, 0);
        requestQueue = Volley.newRequestQueue(context);
        this.a=tab;

        jsArrayRequest = new JsonArrayRequest(Request.Method.GET,URL_BASE+tab,(String) null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONArray response) {
                        items = (ArrayList<DatosCG>) parseJson(response);
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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Referencia del view procesado
        View listItemView;

        DatosCG item = items.get(position);

        //Comprobando si el View no existe
        listItemView = null == convertView ? layoutInflater.inflate(R.layout.activity_canciones,parent,false) : convertView;
        TextView textoTitulo =listItemView.findViewById(R.id.textoTitulo);
        TextView url=listItemView.findViewById(R.id.urlcancion);
        TextView textDescripcion =listItemView.findViewById(R.id.textDescripcion);
        TextView Estado =listItemView.findViewById(R.id.estado);
        textoTitulo.setText(item.getTitulo());
        url.setText(URL_AUDIO+""+item.getArchivo());
        textDescripcion.setText(item.getDescripcion());
        Estado.setText(""+item.getUpdated_at());
        final   ImageView imagenPost = (ImageView) listItemView.findViewById(R.id.imagenPost);
    //imag=item.getImagen_cancion();
        ImageRequest request = new ImageRequest(
                URL_IMG+item.getImagen_cancion(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imagenPost.setImageBitmap(bitmap);
                      //  Log.d(TAG, "Error en respuesta Bitmap: " +URL_IMG+imag);
                    }
                }, 0, 0, null, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imagenPost.setImageResource(R.drawable.subida);
                        Log.d(TAG, "Error en respuesta Bitmap: " + error.getMessage());
                    }
                });
        requestQueue.add(request);


        return listItemView;
    }


    public List<DatosCG> parseJson(JSONArray jsonObject) {
        // Variables locales
        List<DatosCG> posts = new ArrayList<>();


        // Obtener el array del objeto


        for (int i = 0; i < jsonObject.length(); i++) {

            try {
                JSONObject objeto = jsonObject.getJSONObject(i);

                DatosCG post = new DatosCG(
                        objeto.getInt("id"),
                        objeto.getString("titulo"),
                        objeto.getString("genero_id"),
                        objeto.getString("cantante_id"),
                        objeto.getString("descripcion"),
                        objeto.getString("created_at"),
                        objeto.getString("updated_at"),
                        objeto.getString("deleted_at"),
                        objeto.getString("archivo"),
                        objeto.getString("imagen_cancion"),
                        objeto.getString("genero"));

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
