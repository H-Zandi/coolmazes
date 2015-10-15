package ir.coolapps.coolmazes;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.RadialGradient;
import android.os.SystemClock;
import android.util.AttributeSet;

public class MazeView extends View {
	private boolean DEBUG = false;

	private static final float WALL_WIDTH = 5;

	private GameEngine mGameEngine;

	private Mouse mBall;
	private float mBallX;
	private float mBallY;

	private int mWidth;
	private float mXMin;
	private float mYMin;
	private float mXMax;
	private float mYMax;
	private float mUnit;

	private int mMapWidth;
	private int mMapHeight;
	private int[][] mWalls;
	private int[][] mGoals;

	private Paint paint;
	private RadialGradient goalGradient;
	private Matrix matrix = new Matrix();
	private Matrix scaleMatrix = new Matrix();

	private Timer mTimer;
	private long mT1 = 0;
	private long mT2 = 0;
	private int mDrawStep = 0;
	private int mDrawTimeHistorySize = 20;
	private long[] mDrawTimeHistory = new long[mDrawTimeHistorySize];

	Context context;
	String mouseIconName;

	public MazeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (!this.isInEditMode()) {

			goalGradient = new RadialGradient(0, 0, 1, getResources().getColor(
					R.color.goal_highlight), getResources().getColor(
					R.color.goal_shadow), TileMode.MIRROR);

			this.context = context;
			// Set up default Paint values
			paint = new Paint();
			paint.setAntiAlias(true);

			// Calculate geometry
			int w = getWidth();
			int h = getHeight();
			mWidth = Math.min(w, h);
			mXMin = WALL_WIDTH / 2;
			mYMin = WALL_WIDTH / 2;
			mXMax = Math.min(w, h) - WALL_WIDTH / 2;
			mYMax = mXMax;

			if (DEBUG) {
				// Schedule a redraw at 25 Hz
				TimerTask redrawTask = new TimerTask() {
					public void run() {
						postInvalidate();
					}
				};
				mTimer = new Timer(true);
				mTimer.schedule(redrawTask, 0, 1000/* ms *// 25);
			}
		}
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		mWidth = Math.min(w, h);
		mXMax = Math.min(w, h) - WALL_WIDTH / 2;
		mYMax = mXMax;

		calculateUnit();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
		setMeasuredDimension(mWidth, mWidth);
	}

	@Override
	public void onDraw(Canvas canvas) {
		if (!isInEditMode()) {
			// FPS stats
			mT2 = SystemClock.elapsedRealtime();
			long dt = (mT2 - mT1);
			mT1 = mT2;
			mDrawTimeHistory[mDrawStep % mDrawTimeHistorySize] = dt;
			mDrawStep = mDrawStep + 1;

			mBall = mGameEngine.getBall();
			mMapWidth = mGameEngine.getMap().getSizeX();
			mMapHeight = mGameEngine.getMap().getSizeY();

			drawWalls(canvas);
			drawGoals(canvas);
			drawBall(canvas);

			if (DEBUG) {
				// Print FPS
				paint.setColor(Color.WHITE);
				paint.setStyle(Style.STROKE);
				paint.setStrokeWidth(1);
				canvas.drawText("FPS: " + getFPS(), 20, 30, paint);
			}
		}
	}

	public void setGameEngine(GameEngine e) {
		mGameEngine = e;
	}

	public void calculateUnit() {
		if (mGameEngine == null)
			return;

		// Set up geometry
		float xUnit = ((mXMax - mXMin) / mGameEngine.getMap().getSizeX());
		float yUnit = ((mYMax - mYMin) / mGameEngine.getMap().getSizeY());
		mUnit = Math.min(xUnit, yUnit);
	}

	public double getFPS() {
		double avg = 0;
		int n = 0;
		for (long t : mDrawTimeHistory) {
			if (t > 0) {
				avg = avg + t;
				n = n + 1;
			}
		}
		if (n == 0)
			return -1;
		return 1000 * n / avg;
	}

	private void drawWalls(Canvas canvas) {
		paint.setColor(getResources().getColor(R.color.wall));
		paint.setStrokeWidth(WALL_WIDTH);
		paint.setStrokeCap(Cap.ROUND);

		mWalls = mGameEngine.getMap().getWalls();

		for (int y = 0; y < mMapHeight; y++) {
			for (int x = 0; x < mMapWidth; x++) {
				if ((mWalls[y][x] & Wall.TOP) > 0) {
					canvas.drawLine(mXMin + x * mUnit, mYMin + y * mUnit, mXMin
							+ (x + 1) * mUnit, mYMin + y * mUnit, paint);
				}
				if ((mWalls[y][x] & Wall.RIGHT) > 0) {
					canvas.drawLine(mXMin + (x + 1) * mUnit, mYMin + y * mUnit,
							mXMin + (x + 1) * mUnit, mYMin + (y + 1) * mUnit,
							paint);
				}
				if ((mWalls[y][x] & Wall.BOTTOM) > 0) {
					canvas.drawLine(mXMin + x * mUnit, mYMin + (y + 1) * mUnit,
							mXMin + (x + 1) * mUnit, mYMin + (y + 1) * mUnit,
							paint);
				}
				if ((mWalls[y][x] & Wall.LEFT) > 0) {
					canvas.drawLine(mXMin + x * mUnit, mYMin + y * mUnit, mXMin
							+ x * mUnit, mYMin + (y + 1) * mUnit, paint);
				}
			}
		}

		paint.setShader(null);
	}

	private void drawGoals(Canvas canvas) {
		// paint.setShader(goalGradient);
		// paint.setStyle(Style.FILL);
		scaleMatrix.setScale(mUnit, mUnit);

		mGoals = mGameEngine.getMap().getGoals();
		Bitmap bitmap;
		for (int y = 0; y < mMapHeight; y++) {
			for (int x = 0; x < mMapWidth; x++) {
				if (mGoals[y][x] > 0) {
					// matrix.setTranslate(mXMin + x * mUnit, mYMin + y *
					// mUnit);
					// matrix.setConcat(matrix, scaleMatrix);
					// goalGradient.setLocalMatrix(matrix);
					// canvas.drawRect(
					// mXMin + x * mUnit + mUnit / 4,
					// mYMin + y * mUnit + mUnit / 4,
					// mXMin + (x + 1) * mUnit - mUnit / 4,
					// mYMin + (y + 1) * mUnit - mUnit / 4,
					// paint);
					Rect dst = new Rect();
					InputStream inputStream = null;
					try {
						AssetManager assetManager = context.getAssets();
						inputStream = assetManager.open("cheese.png");
						bitmap = BitmapFactory.decodeStream(inputStream);
						dst.set((int) (mXMin + x * mUnit + mUnit / 4),
								(int) (mYMin + y * mUnit + mUnit / 4),
								(int) (mXMin + (x + 1) * mUnit - mUnit / 4),
								(int) (mYMin + (y + 1) * mUnit - mUnit / 4));
						canvas.drawBitmap(bitmap, null, dst, null);
						inputStream.close();
					} catch (IOException e) {
						// silently ignored, bad coder monkey, baaad!
					} finally {
						// we should really close our input streams here.
					}
				}
			}
		}

		// paint.setShader(null);
	}

	private void drawBall(Canvas canvas) {
		if (mBall.getRollDirection() == Direction.UP) {
			mouseIconName = "mouseUp.png";
		} else if (mBall.getRollDirection() == Direction.RIGHT) {
			mouseIconName = "mouseRight.png";
		} else if (mBall.getRollDirection() == Direction.LEFT) {
			mouseIconName = "mouseLeft.png";
		} else if (mBall.getRollDirection() == Direction.DOWN) {
			mouseIconName = "mouseDown.png";
		} else {
			mouseIconName = "mouse.png";
		}
		mBallX = mBall.getX();
		mBallY = mBall.getY();
		Bitmap bitmap;
		// Bitmap bob4444;
		Rect dst = new Rect();
		InputStream inputStream = null;
		try {
			AssetManager assetManager = context.getAssets();
			// inputStream = assetManager.open("bobargb8888.png");
			// BitmapFactory.Options options = new BitmapFactory.Options();
			// options.inPreferredConfig = Bitmap.Config.ARGB_4444;
			// bob4444 = BitmapFactory.decodeStream(inputStream, null, options);
			inputStream = assetManager.open(mouseIconName);
			bitmap = BitmapFactory.decodeStream(inputStream);
			dst.set((int) (mXMin + (mBallX + 0.5f) * mUnit - (mUnit * 0.4f) * 2 / 3),
					(int) (mXMin + (mBallY + 0.5f) * mUnit - (mUnit * 0.4f) * 2 / 3),
					(int) (mXMin + (mBallX + 0.5f) * mUnit + (mUnit * 0.4f) * 2 / 3),
					(int) (mYMin + (mBallY + 0.5f) * mUnit + (mUnit * 0.4f) * 2 / 3));
			canvas.drawBitmap(bitmap, null, dst, null);
			inputStream.close();
		} catch (IOException e) {
			// silently ignored, bad coder monkey, baaad!
		} finally {
			// we should really close our input streams here.
		}
		mBallX = mBall.getX();
		mBallY = mBall.getY();

		paint.setShader(new RadialGradient(mXMin + (mBallX + 0.55f) * mUnit,
				mYMin + (mBallY + 0.55f) * mUnit, mUnit * 0.35f, getResources()
						.getColor(R.color.ball_highlight), getResources()
						.getColor(R.color.ball_shadow), TileMode.MIRROR));

		paint.setStyle(Style.FILL);
		paint.setAlpha(100);
		canvas.drawCircle(mXMin + (mBallX + 0.5f) * mUnit, mYMin
				+ (mBallY + 0.5f) * mUnit, mUnit * 0.4f, paint);
		paint.setShader(null);
	}
}