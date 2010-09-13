package util.coverDetailsList;

import muvibee.media.Music;

@SuppressWarnings("serial")
public class CoverDetailsListEntryMusic extends CoverDetailsListEntry{

	public CoverDetailsListEntryMusic(Music music) {
		super(music, music.getInterpreter());
	}
	
}
