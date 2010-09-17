package util.detailsList;

import muvibee.media.Video;

@SuppressWarnings("serial")
public class DetailsListEntryVideo extends DetailsListEntry{

        private Video video;

	public DetailsListEntryVideo(Video video) {
		super(video, video.getDirector());
                this.video = video;
	}

    public Video getVideo() {
        return video;
    }

}
