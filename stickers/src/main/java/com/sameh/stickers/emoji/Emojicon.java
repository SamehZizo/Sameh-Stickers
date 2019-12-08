/*
 * Copyright 2014 Hieu Rocker
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

package com.sameh.stickers.emoji;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Hieu Rocker (rockerhieu@gmail.com)
 */
public class Emojicon implements Parcelable {

    public static final Creator<Emojicon> CREATOR = new Creator<Emojicon>() {
        @Override
        public Emojicon createFromParcel(Parcel in) {
            return new Emojicon(in);
        }

        @Override
        public Emojicon[] newArray(int size) {
            return new Emojicon[size];
        }
    };

    private int icon;

    private char value;

    private String emoji;

    private int drawable;

    public Emojicon(int icon, char value, String emoji) {
        this.icon = icon;
        this.value = value;
        this.emoji = emoji;
    }

    public Emojicon(Parcel in) {
        this.icon = in.readInt();
        this.value = (char) in.readInt();
        this.emoji = in.readString();
    }

    private Emojicon() {
    }

    public Emojicon(String emoji) {
        this.emoji = emoji;
    }

    // (S)
    public static Emojicon fromResource(Sticker icon) {
        Emojicon emoji = new Emojicon();
        /*emoji.icon = icon;
        emoji.value = (char) value;*/
        emoji.drawable = icon.getDrawable();
        emoji.emoji = "#sticker-" + icon.getName() + "#";
        return emoji;
    }

    public static Emojicon fromResource(int icon, int value) {
        Emojicon emoji = new Emojicon();
        emoji.icon = icon;
        emoji.value = (char) value;
        return emoji;
    }

    public static Emojicon fromCodePoint(int codePoint) {
        Emojicon emoji = new Emojicon();
        emoji.emoji = newString(codePoint);
        return emoji;
    }

    public static Emojicon fromChar(char ch) {
        Emojicon emoji = new Emojicon();
        emoji.emoji = Character.toString(ch);
        return emoji;
    }

    public static Emojicon fromChars(String chars) {
        Emojicon emoji = new Emojicon();
        emoji.emoji = chars;
        return emoji;
    }

    public static final String newString(int codePoint) {
        if (Character.charCount(codePoint) == 1) {
            return String.valueOf(codePoint);
        } else {
            return new String(Character.toChars(codePoint));
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icon);
        dest.writeInt(value);
        dest.writeString(emoji);
        dest.writeInt(drawable);
    }

    public char getValue() {
        return value;
    }

    public int getIcon() {
        return icon;
    }

    public int getDrawable() {
        return drawable;
    }

    public String getEmoji() {
        return emoji;
    }

    public int getImageFromResource(Context context) {
        String imageName = this.getEmoji().split("-")[1].split("#")[0];
        int id = context.getResources().getIdentifier(imageName,"drawable",
                context.getPackageName());
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Emojicon && emoji.equals(((Emojicon) o).emoji);
    }

    @Override
    public int hashCode() {
        return emoji.hashCode();
    }

}
