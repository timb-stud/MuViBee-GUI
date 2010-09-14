package util.coverDetailsList;

import muvibee.media.Music;

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
