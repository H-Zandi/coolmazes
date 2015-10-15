package ir.coolapps.coolmazes;

import ir.coolapps.coolmazes.CustomControls.BkoodakTextView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.DialogInterface.OnKeyListener;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;

public class MazesActivity extends Activity {
	protected PowerManager.WakeLock mWakeLock;

	private MazeView mMazeView;

	private static final int MENU_RESTART = 1;
	private static final int MENU_MAP_PREV = 2;
	private static final int MENU_MAP_NEXT = 3;
	private static final int MENU_SENSOR = 4;
	private static final int MENU_SELECT_MAZE = 5;
	private static final int MENU_ABOUT = 6;

	// private static final int REQUEST_SELECT_MAZE = 1;

	private Dialog mAboutDialog;

	private Intent mSelectMazeIntent;

	private TextView mMazeNameLabel;
	private TextView mRemainingGoalsLabel;
	private TextView mStepsLabel;

	private GestureDetector mGestureDetector;
	private GameEngine mGameEngine;

	private Dialog mMazePause;
	private Dialog mMazeGameOver;

	BkoodakTextView txtTimer;
	
	final int REFRESH_IDLE = 1;
	final int REFRESH_END = 2;
	final int REFRESH_START = 3;
	int doRefreshTimer = REFRESH_END;
	
	int sec = 30;
	int min = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		mWakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK,
				"CoolMazes");

		mSelectMazeIntent = new Intent(MazesActivity.this,
				SelectMazeActivity.class);

		// Build the About Dialog
		mAboutDialog = new Dialog(MazesActivity.this);
		mAboutDialog.setCancelable(true);
		mAboutDialog.setCanceledOnTouchOutside(true);
		mAboutDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mAboutDialog.setContentView(R.layout.about_layout);

		Button aboutDialogOkButton = (Button) mAboutDialog
				.findViewById(R.id.about_ok_button);
		aboutDialogOkButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				mAboutDialog.cancel();
			}
		});

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.game_layout);

		// Show the About Dialog on the first start
		// if (getPreferences(MODE_PRIVATE).getBoolean("firststart", true)) {
		// getPreferences(MODE_PRIVATE).edit().putBoolean("firststart",
		// false).commit();
		// mAboutDialog.show();
		// }

		// Set up game engine and connect it with the relevant views
		mGameEngine = new GameEngine(MazesActivity.this);
		mMazeView = (MazeView) findViewById(R.id.maze_view);
		mGameEngine.setTiltMazesView(mMazeView);
		mMazeView.setGameEngine(mGameEngine);
		mMazeView.calculateUnit();

		mMazeNameLabel = (TextView) findViewById(R.id.maze_name);
		mGameEngine.setMazeNameLabel(mMazeNameLabel);
		mMazeNameLabel.setText(mGameEngine.getMap().getName());
		mMazeNameLabel.invalidate();

		mRemainingGoalsLabel = (TextView) findViewById(R.id.remaining_goals);
		mGameEngine.setRemainingGoalsLabel(mRemainingGoalsLabel);

		mStepsLabel = (TextView) findViewById(R.id.steps);
		mGameEngine.setStepsLabel(mStepsLabel);

		mGameEngine
				.restoreState(savedInstanceState, getPreferences(MODE_PRIVATE)
						.getBoolean("sensorenabled", false));

		// disable sensor in first start
		// if (getPreferences(MODE_PRIVATE).getBoolean("firststart", true)) {
		// getPreferences(MODE_PRIVATE).edit().putBoolean("firststart", false)
		// .commit();
		// }

		// Create gesture detector to detect flings
		mGestureDetector = new GestureDetector(
				new GestureDetector.SimpleOnGestureListener() {
					@Override
					public boolean onDown(MotionEvent e) {
						return true;
					}

					@Override
					public boolean onFling(MotionEvent e1, MotionEvent e2,
							float velocityX, float velocityY) {
						// Roll the ball in the direction of the fling
						Direction mCommandedRollDirection = Direction.NONE;

						if (Math.abs(velocityX) > Math.abs(velocityY)) {
							if (velocityX < 0)
								mCommandedRollDirection = Direction.LEFT;
							else
								mCommandedRollDirection = Direction.RIGHT;
						} else {
							if (velocityY < 0)
								mCommandedRollDirection = Direction.UP;
							else
								mCommandedRollDirection = Direction.DOWN;
						}

						if (mCommandedRollDirection != Direction.NONE) {
							mGameEngine.rollBall(mCommandedRollDirection);
						}

						return true;
					}
				});
		mGestureDetector.setIsLongpressEnabled(false);

		// set the reset btn
		ImageButton btnRetry = (ImageButton) findViewById(R.id.btn_retry);
		btnRetry.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				mGameEngine.sendEmptyMessage(Messages.MSG_RESTART);
				setTempTimer(0, 31);
			}
		});

		// setting btn_pause
		ImageButton btn_pause = (ImageButton) findViewById(R.id.btn_pause);
		btn_pause.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mMazePause.show();
				Statics.vibrate(MazesActivity.this, 30);
			}
		});

		// setting pause dialog
		mMazePause = new Dialog(this);
		mMazePause.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mMazePause.setContentView(R.layout.pause_dialog);
		mMazePause.setCancelable(false);
		mMazePause.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					mMazePause.dismiss();
				}
				return false;
			}
		});
		
		final ImageButton btnMusic = (ImageButton) mMazePause
				.findViewById(R.id.btn_music);
		btnMusic.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(Statics.mp.isPlaying()){
					Statics.mp.pause();
					SharedPreferences prefs = getSharedPreferences("music", MODE_PRIVATE);
		            prefs.edit().putBoolean("shutoff", true).commit();
					btnMusic.setImageResource(R.drawable.no_music);
				}else{
					Statics.mp.start();
					SharedPreferences prefs = getSharedPreferences("music", MODE_PRIVATE);
		            prefs.edit().putBoolean("shutoff", false).commit();
					btnMusic.setImageResource(R.drawable.music);
				}
			}
		});
		
		final ImageButton btnSensor = (ImageButton) mMazePause
				.findViewById(R.id.btn_sensor);
		btnSensor.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mGameEngine.toggleSensorEnabled();
				getPreferences(MODE_PRIVATE).edit()
						.putBoolean("sensorenabled", mGameEngine.isSensorEnabled())
						.commit();
				if(mGameEngine.isSensorEnabled()){
					btnSensor.setImageResource(R.drawable.sensor);
				}
				else{
					btnSensor.setImageResource(R.drawable.no_sensor);
				}
			}
		});
		
		mMazePause.setOnShowListener(new OnShowListener() {
			public void onShow(DialogInterface dialog) {
				setDoRefreshTimer(REFRESH_IDLE);
				if(Statics.mp.isPlaying()){
					btnMusic.setImageResource(R.drawable.music);
				}else{
					btnMusic.setImageResource(R.drawable.no_music);
				}
				if(mGameEngine.isSensorEnabled()){
					btnSensor.setImageResource(R.drawable.sensor);
				}
				else{
					btnSensor.setImageResource(R.drawable.no_sensor);
				}
			}
		});
		
		mMazePause.setOnDismissListener(new OnDismissListener() {
			public void onDismiss(DialogInterface dialog) {
				if(getDoRefreshTimer()!=REFRESH_END){
				    setDoRefreshTimer(REFRESH_START);
				}
			}
		});
		
		ImageButton btnMenu = (ImageButton) mMazePause
				.findViewById(R.id.btn_menu);
		btnMenu.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mMazePause.dismiss();
				setDoRefreshTimer(REFRESH_END);
				finish();
			}
		});
		
		ImageButton btnPrevious = (ImageButton) mMazePause
				.findViewById(R.id.btn_previous);
		btnPrevious.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mGameEngine.sendEmptyMessage(Messages.MSG_MAP_PREVIOUS);				
				mMazePause.dismiss();
				Statics.vibrate(MazesActivity.this, 30);
				setTempTimer(0, 31);
			}
		});
		
		ImageButton btnPlay = (ImageButton) mMazePause
				.findViewById(R.id.btn_reset);
		btnPlay.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mMazePause.dismiss();
				Statics.vibrate(MazesActivity.this, 30);
			}
		});

		ImageButton btnNext = (ImageButton) mMazePause
				.findViewById(R.id.btn_next);
		btnNext.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mGameEngine.sendEmptyMessage(Messages.MSG_MAP_NEXT);
				mMazePause.dismiss();
				Statics.vibrate(MazesActivity.this, 30);
				setTempTimer(0, 31);
			}
		});

		// setting GameOver dialog
		mMazeGameOver = new Dialog(this);
		mMazeGameOver.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mMazeGameOver.setContentView(R.layout.game_over_dialog);
		mMazeGameOver.setCancelable(false);
		mMazeGameOver.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				 if (keyCode == KeyEvent.KEYCODE_BACK) {
					    mMazeGameOver.dismiss();
						setDoRefreshTimer(REFRESH_END);
						finish();
			    }
				return false;
			}
		});
		mMazeGameOver.setOnShowListener(new OnShowListener() {
			public void onShow(DialogInterface dialog) {
				PlayMedia playAudio = new PlayMedia(MediaPlayer.create(
						MazesActivity.this, R.raw.orre));
				playAudio.execute();
				setDoRefreshTimer(REFRESH_IDLE);
			}
		});

		ImageButton btnResetGameOver = (ImageButton) mMazeGameOver
				.findViewById(R.id.btn_reset);
		btnResetGameOver.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mGameEngine.sendEmptyMessage(Messages.MSG_RESTART);
				mMazeGameOver.dismiss();				
				setTempTimer(min, sec + 30);
				txtTimer.setText(fixTimerString(min, sec));
				setDoRefreshTimer(REFRESH_START);
				changeTimer();
				
			}
		});
		ImageButton btnMenuGameOver = (ImageButton) mMazeGameOver
				.findViewById(R.id.btn_menu);
		btnMenuGameOver.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mMazeGameOver.dismiss();
				setDoRefreshTimer(REFRESH_END);
				finish();
			}
		});
		
		PlayMedia playAudio = new PlayMedia(
				MediaPlayer.create(this, R.raw.next));
		playAudio.execute();

		txtTimer = (BkoodakTextView) findViewById(R.id.txtTimer);
		setTempTimer(min, sec);
		txtTimer.setText(fixTimerString(min, sec));
		setDoRefreshTimer(REFRESH_START);
		
		changeTimer();
//	    SharedPreferences MusicCheckprefs = getSharedPreferences("music", MODE_PRIVATE);
//		Boolean prefsCode = MusicCheckprefs.getBoolean("shutoff", false);		
//		if(Statics.mp != null && prefsCode){	
//				Statics.mp.pause();			     	
//		}
	}

	synchronized public void setDoRefreshTimer(int doRefresh) {
		doRefreshTimer = doRefresh;
	}

	synchronized public int getDoRefreshTimer() {
		return doRefreshTimer;
	}
	
	synchronized public void showDialog(){
		mMazeGameOver.show();
		Statics.vibrate(MazesActivity.this, 30);
	}

	int tempSec = sec;
	int tempMin = min;

	synchronized void setTempMin(int min) {
		this.tempMin = min;
	}

	synchronized void setTempSec(int sec) {
		this.tempSec = sec;
	}

	synchronized int getTempMin() {
		return tempMin;
	}

	synchronized int getTempSec() {
		return tempSec;
	}

	public void changeTimer() {
		try {
			final BkoodakTextView txtTimer = (BkoodakTextView) findViewById(R.id.txtTimer);
			final Handler handler = new Handler();
			Runnable runnable = new Runnable() {
				Boolean gameOver = false;

				public void run() {
					if (getDoRefreshTimer() == REFRESH_IDLE) {
						handler.postDelayed(this, 1000);
					} 
					else if(getDoRefreshTimer() == REFRESH_START) {
						if (getTempSec() < 1 && getTempMin() > 0) {
							setTempMin(getTempMin() - 1);
							setTempSec(59);
						} else if (getTempSec() < 1 && getTempMin() < 1) {
							// game over
							showDialog();
							gameOver = true;
						} else {
							setTempSec(getTempSec() - 1);
						}
						if(getTempMin() < 1 && getTempSec() < 10){
							PlayMedia playAudio = new PlayMedia(MediaPlayer.create(
									MazesActivity.this, R.raw.time_beep));
							playAudio.execute();
						}
						txtTimer.setText(fixTimerString(getTempMin(),
								getTempSec()));
						if (!gameOver) {
							handler.postDelayed(this, 1000);
						}
					}
				}
			};
			handler.postDelayed(runnable, 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String fixTimerString(int min, int sec) {
		String timer = "";
		if (min < 10) {
			timer = "0" + String.valueOf(min);
		} else {
			timer = String.valueOf(min);
		}
		if (sec < 10) {
			timer = timer + " : 0" + String.valueOf(sec);
		} else {
			timer = timer + " : " + String.valueOf(sec);
		}
		return timer;
	}

	public void setTempTimer(int min, int sec) {
		if (sec > 59) {
			this.min += sec / 60;
			this.sec = sec % 60;
		} else {
			this.min = min;
			this.sec = sec;
		}
		setTempMin(this.min);
		setTempSec(this.sec);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return mGestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT:
			mGameEngine.rollBall(Direction.LEFT);
			return true;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			mGameEngine.rollBall(Direction.RIGHT);
			return true;
		case KeyEvent.KEYCODE_DPAD_UP:
			mGameEngine.rollBall(Direction.UP);
			return true;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			mGameEngine.rollBall(Direction.DOWN);
			return true;
		case KeyEvent.KEYCODE_BACK:
			// startActivityForResult(mSelectMazeIntent, REQUEST_SELECT_MAZE);
			startActivity(mSelectMazeIntent);
			finish();
			return true;
		default:
			return super.onKeyDown(keyCode, event);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);

		menu.add(0, MENU_MAP_PREV, 0, R.string.menu_map_prev);
		menu.add(0, MENU_RESTART, 0, R.string.menu_restart);
		menu.add(0, MENU_MAP_NEXT, 0, R.string.menu_map_next);
		menu.add(0, MENU_SENSOR, 0, R.string.menu_sensor);
		menu.add(0, MENU_SELECT_MAZE, 0, R.string.menu_select_maze);
		menu.add(0, MENU_ABOUT, 0, R.string.menu_about);

		menu.findItem(MENU_MAP_PREV).setIcon(
				getResources().getDrawable(R.drawable.previous));
		menu.findItem(MENU_RESTART).setIcon(
				getResources().getDrawable(R.drawable.retry));
		menu.findItem(MENU_MAP_NEXT).setIcon(
				getResources().getDrawable(R.drawable.next));
		menu.findItem(MENU_SENSOR).setIcon(
				getResources().getDrawable(R.drawable.sensor));
		menu.findItem(MENU_SELECT_MAZE).setIcon(
				getResources().getDrawable(R.drawable.menu));
		menu.findItem(MENU_ABOUT).setIcon(
				getResources().getDrawable(R.drawable.info));

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_RESTART:
			mGameEngine.sendEmptyMessage(Messages.MSG_RESTART);
			setTempTimer(0, 31);
			return true;

		case MENU_MAP_PREV:
			mGameEngine.sendEmptyMessage(Messages.MSG_MAP_PREVIOUS);
			setTempTimer(0, 31);
			return true;

		case MENU_MAP_NEXT:
			mGameEngine.sendEmptyMessage(Messages.MSG_MAP_NEXT);
			setTempTimer(0, 31);
			return true;

		case MENU_SENSOR:
			mGameEngine.toggleSensorEnabled();
			getPreferences(MODE_PRIVATE).edit()
					.putBoolean("sensorenabled", mGameEngine.isSensorEnabled())
					.commit();
			return true;

		case MENU_SELECT_MAZE:
			// startActivityForResult(mSelectMazeIntent, REQUEST_SELECT_MAZE);
			startActivity(mSelectMazeIntent);
			setDoRefreshTimer(REFRESH_END);
			finish();
			return true;

		case MENU_ABOUT:
			mAboutDialog.show();
			return true;
		}

		return false;
	}

	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// super.onActivityResult(requestCode, resultCode, data);
	//
	// switch (requestCode) {
	// case (REQUEST_SELECT_MAZE):
	// if (resultCode == Activity.RESULT_OK) {
	// int selectedMaze = data.getIntExtra("selected_maze", 0);
	// mGameEngine.loadMap(selectedMaze);
	// }
	// break;
	// case (-1):
	// finish();
	// break;
	// }
	// }

	@Override
	protected void onStart() {
		super.onStart();
		if (Statics.levelSelected) {
			Statics.levelSelected = false;
			mGameEngine.loadMap(Statics.level);
			setDoRefreshTimer(REFRESH_START);
		}
	}

	@Override
	protected void onStop() {
		if(getDoRefreshTimer()!= REFRESH_END){
			setDoRefreshTimer(REFRESH_IDLE);
	    }
		super.onStop();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if(getDoRefreshTimer()!= REFRESH_END){
		setDoRefreshTimer(REFRESH_IDLE);
		}
		mGameEngine.unregisterListener();
		mWakeLock.release();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGameEngine.registerListener();
		setDoRefreshTimer(REFRESH_START);
		mWakeLock.acquire();
	}

	@Override
	public void onSaveInstanceState(Bundle icicle) {
		super.onSaveInstanceState(icicle);
		mGameEngine.saveState(icicle);
		mGameEngine.unregisterListener();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		mGameEngine.restoreState(savedInstanceState,
				getPreferences(MODE_PRIVATE).getBoolean("sensorenabled", true));
	}

	@Override
	protected void onDestroy() {
		setDoRefreshTimer(REFRESH_END);
		super.onDestroy();
	}
}