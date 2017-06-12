package com.linio.android.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.linio.android.R;

/**
 * Created by ricardo.montesinos on 6/7/17.
 */

public class NoSubirActivity extends AppCompatActivity {

    private enum TextViewStyle {
        WhiteTextView,
        WhiteTextViewBold,
        WhiteTextViewBoldLarge,
        GreyTextView,
        GreyTextViewBold,
        GreyTextViewBoldLarge,
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.no_subir_layout);

        //NARANJAS
        setStyle(R.id.modTittle, TextViewStyle.WhiteTextViewBoldLarge, "Tarjetas de memoria", false, false, false);
        setStyle(R.id.modItem, TextViewStyle.WhiteTextView, "Tarjeta SD", true, true, false);
        setStyle(R.id.modVermas, TextViewStyle.WhiteTextView, "Ver todo", true, false, false);
        setStyle(R.id.modAccesorios, TextViewStyle.WhiteTextViewBold, "Accesorios", true, true, false);

        //BLANCOS
        setStyle(R.id.modeTittleBlanco, TextViewStyle.GreyTextViewBoldLarge, "Tarjetas de memoria", false, false, true);
        setStyle(R.id.modeItemBlanco, TextViewStyle.GreyTextView, "Tarjeta SD", true, true, true);
        setStyle(R.id.modeVermasBlanco, TextViewStyle.GreyTextViewBold, "Accesorios", true, true, true);

    }

    public int setTextViewStyle(TextViewStyle textViewStyle) {
        switch (textViewStyle) {
            case WhiteTextView:
                return R.style.WhiteTextView;
            case WhiteTextViewBold:
                return R.style.WhiteTextViewBold;
            case WhiteTextViewBoldLarge:
                return R.style.WhiteTextViewBoldLarge;
            case GreyTextView:
                return R.style.GreyTextView;
            case GreyTextViewBold:
                return R.style.GreyTextViewBold;
            case GreyTextViewBoldLarge:
                return R.style.GreyTextViewBoldLarge;
            default:
                return R.style.WhiteTextView;
        }
    }

    //isClear means white background and gray letters
    private void setStyle(int idLayout, TextViewStyle textViewStyle, String text, boolean showDivider, boolean showIcon, boolean isClear) {

        View view = findViewById(idLayout);
        if (isClear) {
            view.setBackgroundColor(getResources().getColor(R.color.white));
        }

        TextView tvTitle = (TextView) view.findViewById(R.id.tvSubcategoryTitle);
        tvTitle.setTextAppearance(this, setTextViewStyle(textViewStyle));
        tvTitle.setText(text);

        ImageView ivIcon = (ImageView) view.findViewById(R.id.ivSubcategoryIcon);
        ivIcon.setImageResource((isClear) ? R.drawable.nd_ic_add : R.drawable.nd_ic_add_white_24);
        ivIcon.setVisibility((showIcon) ? View.VISIBLE : View.INVISIBLE);

        View divider = (View) view.findViewById(R.id.vDivider);
        if (isClear) {
            divider.setBackgroundColor(getResources().getColor(R.color.gray_300));
        }
        divider.setVisibility((showDivider) ? View.VISIBLE : View.INVISIBLE);
    }

    public void funciona() {
        //NARANJAS
        View view = (View) findViewById(R.id.modeItemBlanco);
        view.setBackgroundColor(getResources().getColor(R.color.white));

        //Typeface tf = Typeface.createFromAsset(getAssets(), "sans-serif");
        TextView tvSubcategoryTitle = (TextView) view.findViewById(R.id.tvSubcategoryTitle);
        //tvSubcategoryTitle.setTypeface(tf);
        tvSubcategoryTitle.setTextAppearance(this, R.style.GreyTextView);
        //tvSubcategoryTitle.setTextSize(R.dimen.text_m); //text_l
        //tvSubcategoryTitle.setTextColor(getResources().getColor(R.color.gray_600));
        tvSubcategoryTitle.setText("Hola paps");

        ImageView ivSubcategoryIcon = (ImageView) view.findViewById(R.id.ivSubcategoryIcon);
        ivSubcategoryIcon.setImageResource(R.drawable.nd_ic_add);

        View divider = (View) view.findViewById(R.id.vDivider);
        divider.setBackgroundColor(getResources().getColor(R.color.gray_300));
    }
}

