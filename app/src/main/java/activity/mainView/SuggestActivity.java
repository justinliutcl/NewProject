package activity.mainView;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.justin.hzwl.myhzwl.R;

import activity.BaseActivity;

public class SuggestActivity extends BaseActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void init() {

    }

    @Override
    public void setListener() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
