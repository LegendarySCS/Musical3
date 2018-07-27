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

import zambanosuarez.musical.DataConexion.DatosGeneros;
import zambanosuarez.musical.R;

public class Generos extends Fragment {
    DatosGeneros adapter2;

    public static Generos newInstance(String sectionTitle) {
        Generos fragment = new Generos();
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
        //gView.setTextFilterEnabled(true);

        // Crear adaptador y setear
        adapter2 = new DatosGeneros(getActivity(),"obtenergeneros",rootView);
        Toast.makeText(Generos.super.getContext(),"Generos", Toast.LENGTH_SHORT).show();

        gView.setAdapter(adapter2);

        gView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 TextView textView = (TextView) view.findViewById(R.id.nombre);
                //Obtiene el texto dentro del TextView.
                String genero  = textView.getText().toString();


              //  Toast.makeText(Generos.super.getContext(),textItemList, Toast.LENGTH_SHORT).show();


                Fragment fragment;
                FragmentManager fragmentManager;
                Bundle args = new Bundle();
                args.putString("genero", genero);


                fragment = CancionesPorGenero.newInstance();
                fragment.setArguments(args);
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.flContent, fragment)
                        .addToBackStack(null)
                        .commit();


                //Bundle bundle = new Bundle();
                //Intent intent = new Intent(view.getContext(), Canciones.class);
                //String texto = ""+textItemList;
                //bundle.putString("idLocal", texto);
                //intent.putExtras(bundle);


                // startActivity(intent);
            }
        });




        return rootView;

    }
}