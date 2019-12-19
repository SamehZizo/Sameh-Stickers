package com.sameh.samehstickers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.sameh.stickers.Actions.StickersActions;
import com.sameh.stickers.Actions.OnClickListner;
import com.sameh.stickers.Helper.EmojiconEditText;
import com.sameh.stickers.Helper.EmojiconTextView;
import com.sameh.stickers.emoji.Emojicon;
import com.sameh.stickers.emoji.MakeStickers;
import com.sameh.stickers.emoji.Sticker;

public class MainActivity extends AppCompatActivity {

    CheckBox mCheckBox;
    EmojiconEditText emojiconEditText, emojiconEditText2;
    EmojiconTextView textView;
    ImageView emojiButton;
    ImageView submitButton;
    View rootView;
    StickersActions emojIcon;
    // (S)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootView = findViewById(R.id.root_view);
        emojiButton = (ImageView) findViewById(R.id.emoji_btn);
        submitButton = (ImageView) findViewById(R.id.submit_btn);
        mCheckBox = (CheckBox) findViewById(R.id.use_system_default);
        emojiconEditText = (EmojiconEditText) findViewById(R.id.emojicon_edit_text);
        emojiconEditText2 = (EmojiconEditText) findViewById(R.id.emojicon_edit_text2);
        textView = (EmojiconTextView) findViewById(R.id.textView);
        image = (ImageView) findViewById(R.id.image);
        emojIcon = new StickersActions(this, rootView, emojiconEditText, emojiButton,4,
                new MakeStickers(
                        new Sticker(R.drawable.ayah,"ayah"),
                        new Sticker(R.drawable.flower_png,"flower_png"),
                        new Sticker(R.drawable.ayah,"ayah"),
                        new Sticker(R.drawable.flower_png,"flower_png"),
                        new Sticker(R.drawable.ayah,"ayah"),
                        new Sticker(R.drawable.flower_png,"flower_png"),
                        new Sticker(R.drawable.ayah,"ayah"),
                        new Sticker(R.drawable.flower_png,"flower_png")
                )
                ,new OnClickListner() {
                    @Override
                    public void onClick(Emojicon emojicon) {
                        Log.d("Sameh",emojicon.getDrawable() + "");
                        image.setImageResource(emojicon.getImageFromResource(MainActivity.this));
                    }
                });
        emojIcon.setKeyboardListener(new StickersActions.KeyboardListener() {
            @Override
            public void onKeyboardOpen() {
                Log.e("Keyboard", "open");
            }

            @Override
            public void onKeyboardClose() {
                Log.e("Keyboard", "close");
            }
        });

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                emojIcon.setUseSystemEmoji(b);
                textView.setUseSystemDefault(b);
            }
        });
        emojIcon.addEmojiconEditTextList(emojiconEditText2);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newText = emojiconEditText.getText().toString();
                textView.setText(newText);
            }
        });
    }


}
