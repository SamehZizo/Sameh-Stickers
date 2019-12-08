package com.sameh.stickers.emoji;

public class MakeStickers {

    private Sticker [] stickers;

    public MakeStickers(Sticker...stickers) {
        this.stickers = stickers;
    }

    public Emojicon [] MakeStickers() {
        Emojicon [] emojicons = new Emojicon[this.stickers.length];
        for (int i = 0 ; i < this.stickers.length ; i++) {
            emojicons[i] = Emojicon.fromResource(this.stickers[i]);
        }
        return emojicons;
    }

}
