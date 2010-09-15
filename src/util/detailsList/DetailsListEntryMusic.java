package util.detailsList;

import muvibee.media.Music;

@SuppressWarnings("serial")
public class DetailsListEntryMusic extends DetailsListEntry{

        private Music music;

	public DetailsListEntryMusic(Music music) {
		super(music, music.getInterpreter());
                this.music = music;
	}

    public Music getMusic() {
        return music;
    }

}
