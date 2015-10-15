package ir.coolapps.coolmazes;

import ir.coolapps.coolmazes.CustomControls.BkoodakTextView;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class SelectMazeActivity extends ListActivity {
	private MazesDBAdapter mDB;
	private Dialog mMazeComment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		validateLevels();
		
        SharedPreferences MusicCheckprefs = getSharedPreferences("music", MODE_PRIVATE);
		Boolean prefsCode = MusicCheckprefs.getBoolean("shutoff", false);
		Statics.playBGSound(getApplicationContext());
		if(prefsCode){
			final Handler handler = new Handler();
			Runnable runnable = new Runnable() {
				public void run() {
					if (Statics.mp != null) {
						try{
							if(Statics.mp.isPlaying()){
								Statics.mp.pause();
							}else{
							    handler.postDelayed(this, 30);
							}
						}catch(Exception ex){
							handler.postDelayed(this, 30);
						}
					}
					else{
						handler.postDelayed(this, 30);
					}
				}
			};
			handler.postDelayed(runnable, 0);		
		}
		
		// setting pause dialog
		mMazeComment = new Dialog(this);
		mMazeComment.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mMazeComment.setContentView(R.layout.comment_dialog);
		mMazeComment.setCancelable(false);
		mMazeComment.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
//				if (keyCode == KeyEvent.KEYCODE_BACK) {
//					mMazeComment.dismiss();
//				}
				return false;
			}
		});
		
		final BkoodakTextView btnComment = (BkoodakTextView) mMazeComment
				.findViewById(R.id.BkoodakTextView1);
		btnComment.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent commentIntent = new Intent("android.intent.action.EDIT", Uri.parse("bazaar://details?id=ir.coolapps.coolmazes"));
	            startActivity(commentIntent);	
	            getPreferences(MODE_PRIVATE).edit()
				.putInt("doComment", 3).commit();
	            mMazeComment.dismiss();
			}
		});
		final BkoodakTextView btnExit = (BkoodakTextView) mMazeComment
				.findViewById(R.id.BkoodakTextView2);
		btnExit.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				SelectMazeActivity.this.finish();
			}
		});
		
	}

	private void validateLevels() {
		mDB = new MazesDBAdapter(getApplicationContext()).open();

		setListAdapter(new CursorAdapter(getApplicationContext(),
				mDB.allMazes(), true) {
			@Override
			public View newView(Context context, Cursor cursor, ViewGroup parent) {
				final LayoutInflater inflater = LayoutInflater.from(context);
				final View rowView = inflater.inflate(
						R.layout.select_maze_row_layout, parent, false);
				bindView(rowView, context, cursor);
				return rowView;
			}

			@Override
			public void bindView(View view, Context context, Cursor cursor) {
				final MapDesign m = MapDesigns.designList.get(cursor
						.getPosition());

				final BkoodakTextView mazeSolutionSteps = (BkoodakTextView) view
						.findViewById(R.id.maze_solution_steps);
				final BkoodakTextView mazeName = (BkoodakTextView) view
						.findViewById(R.id.maze_name);
				final ImageView mazeSolvedTickbox = (ImageView) view
						.findViewById(R.id.maze_solved_tick);
				final BkoodakTextView mazeLevel = (BkoodakTextView) view
						.findViewById(R.id.maze_level);

				if (cursor.getInt(MazesDBAdapter.SOLUTION_STEPS_COLUMN) == 0) {
					mazeSolvedTickbox
							.setImageResource(R.drawable.mouse_hole_wait);
					mazeSolutionSteps.setText("");
				} else {
					mazeSolvedTickbox
							.setImageResource(R.drawable.mouse_hole_win);
					mazeSolutionSteps
							.setText("ÈÇ "
									+ cursor.getString(MazesDBAdapter.SOLUTION_STEPS_COLUMN)
									+ " ÍÑ˜Ê Íá ÔÏå");
				}
//				if (cursor.getPosition() > 2) {
//					mazeSolvedTickbox.setImageResource(R.drawable.lock);
//					mazeSolutionSteps.setText("");
//				}

				mazeName.setText(" (" + m.getSizeX() + "x" + m.getSizeY()
						+ "), " + m.getGoalCount() + " ÊÚÏÇÏ åÏÝ");
				mazeLevel.setText(cursor.getString(MazesDBAdapter.NAME_COLUMN));
			}
		});
		setTitle(R.string.select_maze_title);
		setContentView(R.layout.select_maze_layout);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		// if(firstLoad){
		Intent mMazeIntent = new Intent(this, MazesActivity.class);
		Statics.level = position;
		Statics.levelSelected = true;
		startActivity(mMazeIntent);
		// }else{
		// Intent result = new Intent();
		// result.putExtra("selected_maze", position);
		// setResult(RESULT_OK, result);
		// }
		// finish();
	}
	@Override
	protected void onResume() {	
		super.onPostResume();
//		if(Statics.playAudio !=null && Statics.playAudio.getStatus() !=  AsyncTask.Status.RUNNING){
//			Statics.playBGSound(getApplicationContext());
//		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		validateLevels();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// Show the Comment Dialog if there is no comment
			if (getPreferences(MODE_PRIVATE).getInt("doComment", 0) < 3) {
				getPreferences(MODE_PRIVATE).edit()
						.putInt("doComment", (getPreferences(MODE_PRIVATE).getInt("doComment", 0))+1).commit();
				mMazeComment.show();
			} else {
				finish();
			}
		}
		return true;
	}
	@Override
	protected void onDestroy() {
		if(Statics.playAudio !=null && Statics.playAudio.getStatus() !=  AsyncTask.Status.RUNNING){
			if(Statics.mp.isPlaying()){
			    Statics.mp.stop();
			}
			Statics.mp.release();
			Statics.playAudio.cancel(true);			
	    }else if(Statics.mp != null){
			Statics.mp.release();
		}
		super.onDestroy();		
	}
}
