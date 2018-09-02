/*
 *  Copyright 2018 Steven Smith kana-tutor.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 *  either express or implied.
 *
 *  See the License for the specific language governing permissions
 *  and limitations under the License.
 */
package com.kana_tutor.setedittextalpha;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.graphics.Color.alpha;
import static java.lang.Character.isDigit;


public class SetEditTextAlpha extends AppCompatActivity {
    private static final String TAG = SetEditTextAlpha.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_edit_text_alpha);

        final EditText et = findViewById(R.id.edit_text);
        final Button b = findViewById(R.id.button);
        final int initialAlpha = alpha(b.getCurrentTextColor());

        b.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button)v;
                    String s = b.getText().toString();
                    int [] pct = {0,25,50,75,100};
                    if (v.getTag() == null)
                        v.setTag(0);
                    int idx = (int)v.getTag();
                    v.setTag((idx < pct.length - 1) ? idx + 1 : 0);
                    int currentPct = pct[idx];
                    b.setText(currentPct + "%");
                    int textColor = et.getCurrentTextColor();
                    Log.d(TAG, String.format("color before: 0x%08x", textColor));
                    int alpha = (initialAlpha * currentPct) / 100;
                    // int alpha = ((alpha(textColor) * pct) / 100);
                    textColor = (textColor & 0x0ffffff) | (alpha << 24);
                    et.setTextColor(textColor);
                    Log.d(TAG, String.format("color after: 0x%08x", textColor));
                }
            }
        );

    }
}
