package util.coverDetailsList;

import muvibee.media.Video;

@SuppressWarnings("serial")
public class CoverDetailsListEntryVideo extends CoverDetailsListEntry{

        private Video video;

	public CoverDetailsListEntryVideo(Video video) {
		super(video, video.getDirector());
                this.video = video;
	}

    public Video getVideo() {
        return video;
    }

}
