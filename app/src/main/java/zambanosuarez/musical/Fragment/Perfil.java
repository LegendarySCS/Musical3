package zambanosuarez.musical.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zambanosuarez.musical.DataConexion.DatosArtistas;
import zambanosuarez.musical.R;


public class Perfil extends Fragment {

    public static Perfil newInstance(String sectionTitle) {
        Perfil fragment = new Perfil();



        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView= inflater.inflate(R.layout.perfil, container, false);

        return rootView;
    }
}
