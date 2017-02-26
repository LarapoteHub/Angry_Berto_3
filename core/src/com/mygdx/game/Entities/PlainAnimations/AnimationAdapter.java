package com.mygdx.game.Entities.PlainAnimations;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Multimedia.Sprites;

/**
 * Created by Red Mercy on 2/26/2017.
 */

public class AnimationAdapter extends Animation {


    public AnimationAdapter(float frameDuration, Array<? extends TextureRegion> keyFrames, PlayMode playMode) {
        super(frameDuration, keyFrames, playMode);
    }
    
    public static Array<TextureRegion> splitSheet(Sprite sheet, int FRAME_COLS, int FRAME_ROWS) {
        return splitSheet(sheet, FRAME_COLS, FRAME_ROWS, 0);
    }

    public static Array<TextureRegion> splitSheet(Sprite sheet, int FRAME_COLS, int FRAME_ROWS, int INDEX) {
        Array<TextureRegion> result = new Array();

        TextureRegion[] tReg = sheet.split(sheet.getTexture(), (int) sheet.getWidth() / FRAME_COLS, (int) sheet.getHeight() / FRAME_ROWS)[INDEX];
        result.addAll(tReg);

        return result;
    }

}
