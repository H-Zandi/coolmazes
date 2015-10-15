package ir.coolapps.coolmazes;

import ir.coolapps.coolmazes.CustomControls.BkoodakTextView;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class GameEngine {
	private SensorManager mSensorManager;
	private Vibrator mVibrator;

	private static float ACCEL_THRESHOLD = 2;
	private float mAccelX = 0;
	private float mAccelY = 0;
	@SuppressWarnings("unused")
	private float mAccelZ = 0;

	private Handler mHandler;

	private Map mMap;
	private Mouse mBall;
	private int mCurrentMap = 0;
	private int mMapToLoad = 0;
	private int mStepCount = 0;

	private Direction mCommandedRollDirection = Direction.NONE;

	private TextView mMazeNameLabel;
	private TextView mRemainingGoalsLabel;
	private TextView mStepsView;
	private MazeView mMazeView;

	// private final AlertDialog mMazeSolvedDialog;
	private final AlertDialog mAllMazesSolvedDialog;

	private boolean mSensorEnabled = true;

	private MazesDBAdapter mDB;
	PlayMedia playAudio;
	Context context;

	private final SensorListener mSensorAccelerometer = new SensorListener() {

		public void onSensorChanged(int sensor, float[] values) {
			if (!mSensorEnabled)
				return;

			mAccelX = values[0];
			mAccelY = values[1];
			mAccelZ = values[2];

			mCommandedRollDirection = Direction.NONE;
			if (Math.abs(mAccelX) > Math.abs(mAccelY)) {
				if (mAccelX < -ACCEL_THRESHOLD)
					mCommandedRollDirection = Direction.LEFT;
				if (mAccelX > ACCEL_THRESHOLD)
					mCommandedRollDirection = Direction.RIGHT;
			} else {
				if (mAccelY < -ACCEL_THRESHOLD)
					mCommandedRollDirection = Direction.DOWN;
				if (mAccelY > ACCEL_THRESHOLD)
					mCommandedRollDirection = Direction.UP;
			}
			if (mCommandedRollDirection != Direction.NONE && !mBall.isRolling()) {
				rollBall(mCommandedRollDirection);
			}
		}

		public void onAccuracyChanged(int sensor, int accuracy) {
		}
	};

	public GameEngine(final Context context) {
		// Open maze database
		this.context = context;

		mDB = new MazesDBAdapter(context).open();
		mCurrentMap = mDB.getFirstUnsolved();

		// Request vibrator service
		mVibrator = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);

		// Register the sensor listener
		mSensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(mSensorAccelerometer,
				SensorManager.SENSOR_ACCELEROMETER,
				SensorManager.SENSOR_DELAY_GAME);

		mMap = new Map(MapDesigns.designList.get(mCurrentMap));

		// Create ball
		mBall = new Mouse(this, mMap, mMap.getInitialPositionX(),
				mMap.getInitialPositionY());

		// Congratulations dialog
		// mMazeSolvedDialog = new AlertDialog.Builder(context)
		// .setCancelable(true)
		// .setIcon(android.R.drawable.ic_dialog_info)
		// .setTitle("Congratulations!")
		// .setPositiveButton("Go to next maze!", new
		// DialogInterface.OnClickListener() {
		// public void onClick(DialogInterface dialog, int whichButton) {
		// dialog.cancel();
		// sendEmptyMessage(Messages.MSG_MAP_NEXT);
		// }
		// })
		// .create();
		final Dialog mMazeSolvedDialog = new Dialog(context);
		mMazeSolvedDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mMazeSolvedDialog.setContentView(R.layout.solved_dialog);
		mMazeSolvedDialog.setCancelable(false);
		mMazeSolvedDialog.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					mMazeSolvedDialog.dismiss();
					MazesActivity mMazesActivity = (MazesActivity) context;
					Intent mSelectMazeIntent = new Intent(mMazesActivity,
							SelectMazeActivity.class);
					mMazesActivity.startActivity(mSelectMazeIntent);
				}
				return false;
			}
		});
		mMazeSolvedDialog.setOnShowListener(new OnShowListener() {			
			public void onShow(DialogInterface dialog) {				
				((MazesActivity)context).setDoRefreshTimer(((MazesActivity)context).REFRESH_IDLE);
			}
		});
		mMazeSolvedDialog.setOnDismissListener(new OnDismissListener() {			
			public void onDismiss(DialogInterface dialog) {	
				if(((MazesActivity)context).getDoRefreshTimer() != ((MazesActivity)context).REFRESH_END){
				     ((MazesActivity)context).setDoRefreshTimer(((MazesActivity)context).REFRESH_START);
				}
			}
		});

		ImageButton btnNext = (ImageButton) mMazeSolvedDialog
				.findViewById(R.id.btn_next);
		btnNext.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				sendEmptyMessage(Messages.MSG_MAP_NEXT);
				((MazesActivity)context).setTempTimer(0, 31);
				mMazeSolvedDialog.dismiss();
			}
		});
		ImageButton btnReset = (ImageButton) mMazeSolvedDialog
				.findViewById(R.id.btn_reset);
		btnReset.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				sendEmptyMessage(Messages.MSG_RESTART);
				((MazesActivity)context).setTempTimer(0, 31);
				mMazeSolvedDialog.dismiss();
			}
		});
		ImageButton btnMenu = (ImageButton) mMazeSolvedDialog
				.findViewById(R.id.btn_menu);
		btnMenu.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				mMazeSolvedDialog.dismiss();
				MazesActivity mMazesActivity = (MazesActivity) context;
				Intent mSelectMazeIntent = new Intent(mMazesActivity,
						SelectMazeActivity.class);
				final int REQUEST_SELECT_MAZE = 1;
				mMazesActivity.startActivityForResult(mSelectMazeIntent,
						REQUEST_SELECT_MAZE);
			}
		});

		// Final congratulations dialog
		mAllMazesSolvedDialog = new AlertDialog.Builder(context)
				.setCancelable(true)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Congratulations!")
				.setPositiveButton("OK!",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
								dialog.cancel();
								sendEmptyMessage(Messages.MSG_MAP_NEXT);
							}
						}).create();

		// Create message handler
		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				
				switch (msg.what) {
				case Messages.MSG_INVALIDATE:
					mMazeView.invalidate();
					return;

				case Messages.MSG_REACHED_GOAL:
					mRemainingGoalsLabel.setText("" + mMap.getGoalCount());
					mRemainingGoalsLabel.invalidate();
					vibrate(100);					
					if (mMap.getGoalCount() == 0) {
						// Solved!
						mDB.updateMaze(mCurrentMap, mStepCount);
						// if (mDB.unsolvedMazes().getCount() == 0) {
						// mAllMazesSolvedDialog
						// .setMessage("تبریک!\nشما همه مراحل را با موفقیت پشت سر گذاشتید!\n"
						// + "! :)");
						// mAllMazesSolvedDialog.show();
						// } else {
						// mMazeSolvedDialog.setMessage(
						// "You have solved maze "
						// + mMap.getName()
						// + " in " + mStepCount + " steps."
						// );
						BkoodakTextView tvScore = (BkoodakTextView) mMazeSolvedDialog
								.findViewById(R.id.tvScore);
						tvScore.setText("مرحله " + mMap.getName() + " با "
								+ mStepCount + " حرکت");
						mMazeSolvedDialog.show();
						int[] soundIDs = {R.raw.eat,R.raw.yes};
						playAudio = new PlayMedia(context,soundIDs);
						playAudio.execute();
						// }
					}
					else{
						playAudio = new PlayMedia(MediaPlayer.create(context, R.raw.eat));
						playAudio.execute();
					}
					return;

				case Messages.MSG_REACHED_WALL:				
					vibrate(12);	
					playAudio = new PlayMedia(MediaPlayer.create(context, R.raw.wall));
					playAudio.execute();
					return;

				case Messages.MSG_RESTART:
					loadMap(mCurrentMap);
					vibrate(30);
					playAudio = new PlayMedia(MediaPlayer.
							create(context, R.raw.orre));
					playAudio.execute();
					return;

				case Messages.MSG_MAP_PREVIOUS:
				case Messages.MSG_MAP_NEXT:
					vibrate(30);
					switch (msg.what) {
					case (Messages.MSG_MAP_PREVIOUS):						
						if (mCurrentMap == 0) {
							// Wrap around
							mMapToLoad = MapDesigns.designList.size() - 1;
							
						} else {
							mMapToLoad = (mCurrentMap - 1)
									% MapDesigns.designList.size();
						}
					    playAudio = new PlayMedia(MediaPlayer.
							create(context, R.raw.previous));
					    playAudio.execute();
						break;

					case (Messages.MSG_MAP_NEXT):
						mMapToLoad = (mCurrentMap + 1)
								% MapDesigns.designList.size();
					    playAudio = new PlayMedia(MediaPlayer.
							create(context, R.raw.next));
					    playAudio.execute();
						break;
					}

					loadMap(mMapToLoad);
					return;
				}

				super.handleMessage(msg);
			}
		};
	}

	public void loadMap(int mapID) {
		mCurrentMap = mapID;
		mBall.stop();
		mMap = new Map(MapDesigns.designList.get(mCurrentMap));
		mBall.setMap(mMap);
		mBall.setX(mMap.getInitialPositionX());
		mBall.setY(mMap.getInitialPositionY());
		mBall.setXTarget(mMap.getInitialPositionX());
		mBall.setYTarget(mMap.getInitialPositionY());
		mMap.init();

		mStepCount = 0;

		mMazeNameLabel.setText(mMap.getName());
		mMazeNameLabel.invalidate();

		mRemainingGoalsLabel.setText("" + mMap.getGoalCount());
		mRemainingGoalsLabel.invalidate();

		mStepsView.setText("" + mStepCount);
		mStepsView.invalidate();

		mMazeView.calculateUnit();
		mMazeView.invalidate();
	}

	public void setMazeNameLabel(TextView mazeNameLabel) {
		mMazeNameLabel = mazeNameLabel;
	}

	public void setRemainingGoalsLabel(TextView remainingGoalsLabel) {
		mRemainingGoalsLabel = remainingGoalsLabel;
	}

	public void setTiltMazesView(MazeView mazeView) {
		mMazeView = mazeView;
		mBall.setMazeView(mazeView);
	}

	public void setStepsLabel(TextView stepsView) {
		mStepsView = stepsView;
	}

	public void sendEmptyMessage(int msg) {
		mHandler.sendEmptyMessage(msg);
	}

	public void sendMessage(Message msg) {
		mHandler.sendMessage(msg);
	}

	public void registerListener() {
		mSensorManager.registerListener(mSensorAccelerometer,
				SensorManager.SENSOR_ACCELEROMETER,
				SensorManager.SENSOR_DELAY_GAME);
	}

	public void unregisterListener() {
		mSensorManager.unregisterListener(mSensorAccelerometer);
	}

	public void rollBall(Direction dir) {
		if (mBall.roll(dir))
			mStepCount++;		
		mStepsView.setText("" + mStepCount);
		mStepsView.invalidate();
//		playAudio = new PlayMedia(MediaPlayer.
//				create(context, R.raw.wall));
//		playAudio.execute();
	}

	public Mouse getBall() {
		return mBall;
	}

	public Map getMap() {
		return mMap;
	}

	public boolean isSensorEnabled() {
		return mSensorEnabled;
	}

	public void toggleSensorEnabled() {
		mSensorEnabled = !mSensorEnabled;
	}

	public void vibrate(long milliseconds) {
		mVibrator.vibrate(milliseconds);
	}

	public void saveState(Bundle icicle) {
		mBall.stop();

		icicle.putInt("map.id", mCurrentMap);

		int[][] goals = mMap.getGoals();
		int sizeX = mMap.getSizeX();
		int sizeY = mMap.getSizeY();
		int[] goalsToSave = new int[sizeX * sizeY];
		for (int y = 0; y < sizeY; y++)
			for (int x = 0; x < sizeX; x++)
				goalsToSave[y + x * sizeX] = goals[y][x];
		icicle.putIntArray("map.goals", goalsToSave);

		icicle.putInt("stepcount", mStepCount);

		icicle.putInt("ball.x", Math.round(mBall.getX()));
		icicle.putInt("ball.y", Math.round(mBall.getY()));
	}

	public void restoreState(Bundle icicle, boolean sensorEnabled) {
		if (icicle != null) {
			int mapID = icicle.getInt("map.id", -1);
			if (mapID == -1)
				return;
			loadMap(mapID);

			int[] goals = icicle.getIntArray("map.goals");
			if (goals == null)
				return;

			int sizeX = mMap.getSizeX();
			int sizeY = mMap.getSizeY();
			for (int y = 0; y < sizeY; y++)
				for (int x = 0; x < sizeX; x++)
					mMap.setGoal(x, y, goals[y + x * sizeX]);

			mBall.setX(icicle.getInt("ball.x"));
			mBall.setY(icicle.getInt("ball.y"));

			// We have probably moved the ball, so invalidate the Maze View
			mMazeView.invalidate();

			mStepCount = icicle.getInt("stepcount", 0);
		}
		mRemainingGoalsLabel.setText("" + mMap.getGoalCount());
		mRemainingGoalsLabel.invalidate();

		mStepsView.setText("" + mStepCount);
		mStepsView.invalidate();

		mSensorEnabled = sensorEnabled;
	}
}
