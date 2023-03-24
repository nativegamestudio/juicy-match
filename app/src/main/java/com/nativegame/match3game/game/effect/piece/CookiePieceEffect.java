package com.nativegame.match3game.game.effect.piece;

import com.nativegame.match3game.game.layer.Layer;
import com.nativegame.nattyengine.engine.Engine;
import com.nativegame.nattyengine.entity.sprite.Sprite;
import com.nativegame.nattyengine.entity.sprite.modifier.FadeOutModifier;
import com.nativegame.nattyengine.entity.sprite.modifier.RotationModifier;
import com.nativegame.nattyengine.texture.Texture;
import com.nativegame.nattyengine.util.math.RandomUtils;

public class CookiePieceEffect extends Sprite {

    private static final long TIME_TO_LIVE = 500;

    private final CookiePiece mCookiePiece;
    private final RotationModifier mRotationModifier;
    private final FadeOutModifier mFadeOutModifier;

    private float mSpeedX;
    private float mSpeedY;

    public CookiePieceEffect(Engine engine, Texture texture, CookiePiece cookiePiece) {
        super(engine, texture);
        mCookiePiece = cookiePiece;
        mRotationModifier = new RotationModifier(0, RandomUtils.nextSign() * 30f, TIME_TO_LIVE);
        mFadeOutModifier = new FadeOutModifier(TIME_TO_LIVE);
        mFadeOutModifier.setAutoRemove(true);
        setLayer(Layer.EFFECT_LAYER);
    }

    //--------------------------------------------------------
    // Getter and Setter
    //--------------------------------------------------------
    public CookiePiece getCookiePiece() {
        return mCookiePiece;
    }
    //========================================================

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    public void onUpdate(long elapsedMillis) {
        mX += mSpeedX * elapsedMillis;
        mY += mSpeedY * elapsedMillis;
        mRotationModifier.update(this, elapsedMillis);
        mFadeOutModifier.update(this, elapsedMillis);
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    public void activate(float x, float y) {
        setCenterX(x);
        setCenterY(y);
        mSpeedX = mCookiePiece.getDirectionX() * 200f / 1000;
        mSpeedY = mCookiePiece.getDirectionY() * 200f / 1000;
        mRotationModifier.init(this);
        mFadeOutModifier.init(this);
        addToGame();
    }
    //========================================================

}
