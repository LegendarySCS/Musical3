package zambanosuarez.musical.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import zambanosuarez.musical.DataConexion.DatosArtistas;

import zambanosuarez.musical.R;


public class Artistas extends Fragment {
    DatosArtistas adapter2;
    public static Artistas newInstance(String sectionTitle) {
        Artistas fragment = new Artistas();



        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_generales, container, false);


        ListView gView = (ListView) rootView.findViewById(R.id.listView);
        //SearchView sv= (SearchView) rootView.findViewById(R.id.sv);
        gView.setTextFilterEnabled(true);

        // Crear adaptador y setear
        adapter2 = new DatosArtistas(getActivity(),"artista",rootView);
        gView.setAdapter(adapter2);

        gView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.textoTitulo);
                //Obtiene el texto dentro del TextView.
                String artistas  = textView.getText().toString();

//mensaje Toast
                Toast.makeText(Artistas.super.getContext(),artistas, Toast.LENGTH_SHORT).show();
                Fragment fragment;
                FragmentManager fragmentManager;
                Bundle args = new Bundle();
                args.putString("artista", artistas);
//creamos un fragment y lo remplazamos para cargar las vistas

                fragment = DatosArtista.newInstance();
                fragment.setArguments(args);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flContent, fragment)
                        .addToBackStack(null)
                        .commit();



            }
        });




        return rootView;
    }
}
