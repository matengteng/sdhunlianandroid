package newair.com.marriagenetwork.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.animation.Animation;

/**
 * Created by mateng on 16/1/21.
 */
public class FragmentBase extends Fragment {


    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
