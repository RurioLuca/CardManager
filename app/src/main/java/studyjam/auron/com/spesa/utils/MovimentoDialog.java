package studyjam.auron.com.spesa.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import studyjam.auron.com.spesa.R;

/**
 * Created by luca on 4/27/16.
 */
public class MovimentoDialog extends Dialog {


    public MovimentoDialog(Context context) {
        super(context, R.style.DialogTheme);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_addmovimento);

    }
}
