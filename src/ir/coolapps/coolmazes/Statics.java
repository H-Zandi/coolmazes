package ir.coolapps.coolmazes;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;

public final class Statics {
	public static int level;
	public static boolean levelSelected = false;
	public static boolean canResume = false;
	public static PlayMedia playAudio;
	public static MediaPlayer mp;
	public static Context currentContext;
	
	public static void playBGSound(Context context){
		currentContext = context;		
		new Handler().postDelayed(new Runnable() {
		  public void run() {		
			  mp = MediaPlayer.create(currentContext, R.raw.bg);
		      playAudio = new PlayMedia(mp,true);
			  playAudio.execute(); 
		  }
		}, 500);
	}
	
	public static void vibrate(Context context,long milliseconds) {
		// Request vibrator service
		Vibrator mVibrator = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
		mVibrator.vibrate(milliseconds);
	}
}
