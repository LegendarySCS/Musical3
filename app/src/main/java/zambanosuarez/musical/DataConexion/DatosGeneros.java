package zambanosuarez.musical.DataConexion;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zambanosuarez.musical.R;

public class DatosGeneros extends ArrayAdapter {

    private static final String URL_BASE = "http://168.62.42.172/ciudadmusical/";
    private static final String URL_IMG = "";
    private static final String TAG = "DatosGeneros";
    JsonArrayRequest jsArrayRequest;

    ArrayList<DatosG> items;
    Context context;
    LayoutInflater layoutInflater;
    private RequestQueue requestQueue;
    private ProgressBar progressBar;
String a;
    public DatosGeneros(Context context, String tab, final View View22) {
        super(context, 0);
        requestQueue = Volley.newRequestQueue(context);
this.a=tab;

        jsArrayRequest = new JsonArrayRequest(Request.Method.GET,URL_BASE+tab,(String) null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONArray response) {
                        items = (ArrayList<DatosG>) parseJson(response);
                        notifyDataSetChanged();
                        //Log.d(TAG, URL_BASE+a+"");

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

        DatosG item = items.get(position);

        //Comprobando si el View no existe
        listItemView = null == convertView ? layoutInflater.inflate(R.layout.activity_generales2,parent,false) : convertView;
        TextView textxt =listItemView.findViewById(R.id.nombre);
        TextView fecha =listItemView.findViewById(R.id.fechacreado);
        TextView fehadele =listItemView.findViewById(R.id.fechadele);
        textxt.setText(item.getGenero());
        //fecha.setText(item.getUpdated_at());
        //fehadele.setText(""+item.getId());

        return listItemView;
    }


    public List<DatosG> parseJson(JSONArray jsonObject) {
        // Variables locales
        List<DatosG> posts = new ArrayList<>();


        // Obtener el array del objeto


        for (int i = 0; i < jsonObject.length(); i++) {

            try {
                JSONObject objeto = jsonObject.getJSONObject(i);

                DatosG post = new DatosG(
                        objeto.getInt("id"),
                        objeto.getString("genero"),
                        objeto.getString("created_at"),
                        objeto.getString("deleted_at"),
                        objeto.getString("updated_at"));
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
