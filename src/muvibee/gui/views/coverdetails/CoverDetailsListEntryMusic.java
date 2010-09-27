package muvibee.gui.views.coverdetails;

import muvibee.media.Music;

/**
 * Spezialisierte für Musik. Super Klasse ist CoverDetailsListEntry
 * @author Christian Rech
 */

@SuppressWarnings("serial")
public class CoverDetailsListEntryMusic extends CoverDetailsListEntry{

        private Music music;

	public CoverDetailsListEntryMusic(Music music) {
		super(music, music.getInterpreter());
                this.music = music;
	}

    public Music getMusic() {
        return music;
    }

}
