package zambanosuarez.musical.Fragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import zambanosuarez.musical.DataConexion.DatosCanciones;
import zambanosuarez.musical.DataConexion.DatosCancionesPorGenero;
import zambanosuarez.musical.DataConexion.DatosGeneros;
import zambanosuarez.musical.DescargaTask;
import zambanosuarez.musical.R;


public class Canciones extends Fragment {
    DatosCanciones adapter2;
    String urlcancion;
    private MediaPlayer mediaPlayer;
    public static Canciones newInstance(String sectionTitle) {
        Canciones fragment = new Canciones();
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
        adapter2 = new DatosCanciones(getActivity(),"obtenercanciones",rootView);
        Toast.makeText(Canciones.super.getContext(),"Canciones", Toast.LENGTH_SHORT).show();

        gView.setAdapter(adapter2);
        //instanciamos El media player para que se reprosdusca la musica
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);




        gView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.urlcancion);
                final TextView textView22 = (TextView) view.findViewById(R.id.textoTitulo);
                //Obtiene el texto dentro del TextView.
                 urlcancion  = textView.getText().toString();

                TextView boton=view.findViewById(R.id.Boton);
                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       Toast.makeText(Canciones.super.getContext(),"Descargando....", Toast.LENGTH_SHORT).show();
                    //crearArchivo(urlcancion)
                    DescargaTask as=new DescargaTask();
                        as.execute(urlcancion, textView22.getText().toString());

                    }
                });


                  Toast.makeText(Canciones.super.getContext(),urlcancion, Toast.LENGTH_SHORT).show();
                try {
                    if(mediaPlayer.isPlaying()){
                        Toast.makeText(Canciones.super.getContext(),"Pausa", Toast.LENGTH_SHORT).show();
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }else {
                        // Toast.makeText(DatosArtista.super.getContext(),"No", Toast.LENGTH_SHORT).show();
                        mediaPlayer.setDataSource(urlcancion);
                        Toast.makeText(Canciones.super.getContext(),"Reproduciendo", Toast.LENGTH_SHORT).show();

                        //textView.setText(textItemList+"1111");
                        mediaPlayer.prepare();
                        mediaPlayer.start();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });




        return rootView;
    }

}
