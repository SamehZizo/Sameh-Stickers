/*
 * Copyright 2016 Hani Al Momani
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



package com.sameh.stickers.Helper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

import com.sameh.stickers.Actions.OnClickListner;
import com.sameh.stickers.R;
import com.sameh.stickers.emoji.Emojicon;


/**
 * @author Hani Al Momani (hani.momanii@gmail.com)
 */

class EmojiAdapter extends ArrayAdapter<Emojicon> {
    private boolean mUseSystemDefault = false;
    EmojiconGridView.OnEmojiconClickedListener emojiClickListener;

    // (S)
    OnClickListner onClickListner;


    public EmojiAdapter(Context context, List<Emojicon> data, boolean useSystemDefault) {
        super(context, R.layout.emojicon_item, data);
        mUseSystemDefault = useSystemDefault;
    }

    public EmojiAdapter(Context context, Emojicon[] data, boolean useSystemDefault) {
        super(context, R.layout.emojicon_item, data);
        mUseSystemDefault = useSystemDefault;
    }


    /*public void setEmojiClickListener(EmojiconGridView.OnEmojiconClickedListener listener){
        this.emojiClickListener = listener;
    }*/

    public void setEmojiClickListener(OnClickListner listener){
        this.onClickListner= listener;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = View.inflate(getContext(), R.layout.emojicon_item, null);
            ViewHolder holder = new ViewHolder();
            holder.icon = (ImageView) v.findViewById(R.id.emojicon_icon);
            //holder.icon = (EmojiconTextView) v.findViewById(R.id.emojicon_icon);
            //holder.icon.setUseSystemDefault(mUseSystemDefault);

            v.setTag(holder);
        }

         Emojicon emoji = getItem(position);
         ViewHolder holder = (ViewHolder) v.getTag();
             //holder.icon.setText(emoji.getEmoji());
             holder.icon.setImageDrawable(getContext().getResources()
                     .getDrawable(emoji.getDrawable()));
                holder.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickListner.onClick(getItem(position));
                        //emojiClickListener.onEmojiconClicked(getItem(position));
                        //Log.d("Sameh","clicked");
                    }
                });

        return v;
    }

    class ViewHolder {
        ImageView icon;
        //EmojiconTextView icon;
    }
}