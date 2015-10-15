package ir.coolapps.coolmazes;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
import android.util.Log;

public class PlayMedia extends AsyncTask<Void, Void, Void> {

	private static final String LOG_TAG = PlayMedia.class.getSimpleName();

	Context context;
	MediaPlayer mediaPlayer;
	int[] soundIDs;
	int idx =1;
	Boolean hasLoop;

	public PlayMedia(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}
	public PlayMedia(MediaPlayer mediaPlayer,Boolean loop) {
		this.mediaPlayer = mediaPlayer;
		this.hasLoop = loop;
		this.mediaPlayer.setLooping(loop);
	}
	public PlayMedia(final Context context, final int[] soundIDs) {
		this.context = context;
		this.soundIDs=soundIDs;
		mediaPlayer = MediaPlayer.create(context,soundIDs[0]);
        setNextMediaForMediaPlayer(mediaPlayer);
	}
	
	public void setNextMediaForMediaPlayer(MediaPlayer player){
		player.setOnCompletionListener(new OnCompletionListener() {			
			public void onCompletion(MediaPlayer mp) {
				if(soundIDs.length>idx){
					mp.release();
					mp = MediaPlayer.create(context,soundIDs[idx]);
					setNextMediaForMediaPlayer(mp);
					mp.start();
					idx+=1;
				}				
			}
		});
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			mediaPlayer.start();
		} catch (IllegalArgumentException e) {
			Log.e(LOG_TAG, "", e);
		} catch (SecurityException e) {
			Log.e(LOG_TAG, "", e);
		} catch (IllegalStateException e) {
			Log.e(LOG_TAG, "", e);
		}

		return null;
	}
}
