package zambanosuarez.musical.Fragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import zambanosuarez.musical.DataConexion.DatosCancionesPorGenero;
import zambanosuarez.musical.DescargaTask;
import zambanosuarez.musical.R;

public class CancionesPorGenero extends Fragment {
    DatosCancionesPorGenero adapter2;
    private MediaPlayer mediaPlayer;
    public static CancionesPorGenero newInstance() {
        CancionesPorGenero fragment = new CancionesPorGenero();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView= inflater.inflate(R.layout.canciones_genero, container, false);

        String genero = getArguments() != null ? getArguments().getString("genero") : ".-.";
        ListView gView = (ListView) rootView.findViewById(R.id.listView);

        Toast.makeText(CancionesPorGenero.super.getContext(),genero, Toast.LENGTH_SHORT).show();
        adapter2 = new DatosCancionesPorGenero(getActivity(),genero,rootView);
        gView.setAdapter(adapter2);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
       // final View rootView1= inflater.inflate(R.layout.app_bar_activity_repro, container, false);
        //FloatingActionButton fab=rootView1.findViewById(R.id.fab);


        gView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.urlcancion);
                final TextView textView22 = (TextView) view.findViewById(R.id.textoTitulo);

                //Obtiene el texto dentro del TextView.
                final String genero  = textView.getText().toString();

                TextView boton=view.findViewById(R.id.Boton);
                boton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(CancionesPorGenero.super.getContext(),"Descargando....", Toast.LENGTH_SHORT).show();
                        //crearArchivo(urlcancion)
                        DescargaTask as=new DescargaTask();
                        as.execute(genero, textView22.getText().toString());

                    }
                });

                  Toast.makeText(CancionesPorGenero.super.getContext(),genero, Toast.LENGTH_SHORT).show();

                try {
                    if(mediaPlayer.isPlaying()){
                        Toast.makeText(CancionesPorGenero.super.getContext(),"Pausa", Toast.LENGTH_SHORT).show();
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }else {
                        // Toast.makeText(DatosArtista.super.getContext(),"No", Toast.LENGTH_SHORT).show();
                        mediaPlayer.setDataSource(genero);
                        Toast.makeText(CancionesPorGenero.super.getContext(),"Reproduciendo", Toast.LENGTH_SHORT).show();

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
