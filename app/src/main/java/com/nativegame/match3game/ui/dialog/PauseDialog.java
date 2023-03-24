package com.nativegame.match3game.ui.dialog;

import android.view.View;
import android.widget.TextView;

import com.nativegame.match3game.R;
import com.nativegame.match3game.asset.Preferences;
import com.nativegame.match3game.level.Level;
import com.nativegame.nattyengine.audio.music.MusicManager;
import com.nativegame.nattyengine.audio.sound.SoundManager;
import com.nativegame.nattyengine.ui.GameActivity;
import com.nativegame.nattyengine.ui.GameButton;
import com.nativegame.nattyengine.util.resource.ResourceUtils;

public class PauseDialog extends BaseDialog {

    private int mSelectedId;

    public PauseDialog(GameActivity activity) {
        super(activity);
        setContentView(R.layout.dialog_pause);
        setContainerView(R.layout.dialog_container);
        setEnterAnimationId(R.anim.enter_from_center);
        setExitAnimationId(R.anim.exit_to_center);

        GameButton btnMusic = (GameButton) findViewById(R.id.btn_music);
        btnMusic.popUp(200, 300);
        btnMusic.setOnClickListener(this);
        GameButton btnSound = (GameButton) findViewById(R.id.btn_sound);
        btnSound.popUp(200, 400);
        btnSound.setOnClickListener(this);
        GameButton btnQuit = (GameButton) findViewById(R.id.btn_quit);
        btnQuit.popUp(200, 500);
        btnQuit.setOnClickListener(this);
        GameButton btnResume = (GameButton) findViewById(R.id.btn_resume);
        btnResume.setOnClickListener(this);
        TextView txtLevel = (TextView) findViewById(R.id.txt_level);
        txtLevel.setText(ResourceUtils.getString(activity, R.string.txt_level, Level.LEVEL_DATA.getLevel()));

        updateMusicButton();
        updateSoundButton();
    }

    //--------------------------------------------------------
    // Overriding methods
    //--------------------------------------------------------
    @Override
    protected void onHide() {
        if (mSelectedId == R.id.btn_quit) {
            quitGame();
        } else {
            resumeGame();
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.btn_music) {
            toggleMusic();
            updateMusicButton();
        } else if (id == R.id.btn_sound) {
            toggleSound();
            updateSoundButton();
        } else if (id == R.id.btn_quit) {
            mSelectedId = id;
            dismiss();
        } else if (id == R.id.btn_resume) {
            mSelectedId = id;
            dismiss();
        }
    }
    //========================================================

    //--------------------------------------------------------
    // Methods
    //--------------------------------------------------------
    private void toggleMusic() {
        MusicManager musicManager = mParent.getMusicManager();
        boolean enable = musicManager.isAudioEnable();
        musicManager.setAudioEnable(!enable);
        // Save to Preference
        Preferences.PREF_SETTING.putBoolean(Preferences.KEY_MUSIC, !enable);
    }

    private void toggleSound() {
        SoundManager soundManager = mParent.getSoundManager();
        boolean enable = soundManager.isAudioEnable();
        soundManager.setAudioEnable(!enable);
        // Save to Preference
        Preferences.PREF_SETTING.putBoolean(Preferences.KEY_SOUND, !enable);
    }

    private void updateMusicButton() {
        boolean enable = mParent.getMusicManager().isAudioEnable();
        GameButton btnMusic = (GameButton) findViewById(R.id.btn_music);
        if (enable) {
            btnMusic.setBackgroundResource(R.drawable.btn_music_on);
        } else {
            btnMusic.setBackgroundResource(R.drawable.btn_music_off);
        }
    }

    private void updateSoundButton() {
        boolean enable = mParent.getSoundManager().isAudioEnable();
        GameButton btnSound = (GameButton) findViewById(R.id.btn_sound);
        if (enable) {
            btnSound.setBackgroundResource(R.drawable.btn_sound_on);
        } else {
            btnSound.setBackgroundResource(R.drawable.btn_sound_off);
        }
    }

    public void quitGame() {
    }

    public void resumeGame() {
    }
    //========================================================

}
