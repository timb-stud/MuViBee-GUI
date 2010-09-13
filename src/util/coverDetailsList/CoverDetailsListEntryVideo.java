package util.coverDetailsList;

import muvibee.media.Video;

@SuppressWarnings("serial")
public class CoverDetailsListEntryVideo extends CoverDetailsListEntry{

	public CoverDetailsListEntryVideo(Video video) {
		super(video, video.getDirector());
	}
	
}
