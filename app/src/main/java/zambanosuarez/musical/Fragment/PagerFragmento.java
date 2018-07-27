package zambanosuarez.musical.Fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerFragmento extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerFragmento(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Artistas tab0 = new Artistas();
                return tab0;
            case 1:
                Canciones tab1 = new Canciones();
                return tab1;

            case 2:
                Generos tab2 = new Generos();
                return tab2;
            default:
                throw new RuntimeException("Tab position invalid " + position);

        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}