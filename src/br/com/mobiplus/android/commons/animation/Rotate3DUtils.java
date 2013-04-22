package br.com.mobiplus.android.commons.animation;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

public class Rotate3DUtils {

	private static final int DURATION = 600;
	private ViewGroup mContainer;
	private boolean first;
	private View mFirstView;
	private View mSecondView;
	private AnimationCallbackListener mCallback;

	private float depthZ = 310.0f;
	
	public Rotate3DUtils(ViewGroup container) {
		first = true;
		mContainer = container;
	}

	public void applyRotation() {

		if (first) {
			applyRotation(1, 0, -90);
			first = false;
		} else {
			applyRotation(-1, 0, 90);
			first = true;
		}
	}

	public void applyRotation(AnimationCallbackListener callback) {
		mCallback = callback;
		applyRotation();
	}

	/**
	 * Setup a new 3D rotation on the container view.
	 * 
	 * @param position
	 *            the item that was clicked to show a picture, or -1 to show the list
	 * @param start
	 *            the start angle at which the rotation must begin
	 * @param end
	 *            the end angle of the rotation
	 */
	private void applyRotation(int position, float start, float end) {
		// Find the center of the container
		final float centerX = mContainer.getWidth() / 2.0f;
		final float centerY = mContainer.getHeight() / 2.0f;

		// Create a new 3D rotation with the supplied parameter
		// The animation listener is used to trigger the next animation
		final Rotate3dAnimation rotation = new Rotate3dAnimation(start, end, centerX, centerY, depthZ, true);
		rotation.setDuration(DURATION);
		rotation.setFillAfter(true);
		rotation.setInterpolator(new AccelerateInterpolator());
		rotation.setAnimationListener(new DisplayNextView(position));

		mContainer.startAnimation(rotation);
	}

	/**
	 * This class listens for the end of the first half of the animation. It then posts a new action that effectively swaps the views when the container is rotated 90 degrees and thus invisible.
	 */
	private final class DisplayNextView implements Animation.AnimationListener {
		private final int mPosition;

		private DisplayNextView(int position) {
			mPosition = position;
		}

		public void onAnimationStart(Animation animation) {
		}

		public void onAnimationEnd(Animation animation) {
			mContainer.post(new SwapViews(mPosition));
		}

		public void onAnimationRepeat(Animation animation) {

		}
	}

	/**
	 * This class listens for the end of the first half of the animation. It then posts a new action that effectively swaps the views when the container is rotated 90 degrees and thus invisible.
	 */
	private final class DisplayPreviewsView implements Animation.AnimationListener {

		public void onAnimationStart(Animation animation) {
		}

		public void onAnimationEnd(Animation animation) {
			if (mCallback != null) {
				mCallback.animationFinish();
			}
		}

		public void onAnimationRepeat(Animation animation) {

		}
	}

	/**
	 * This class is responsible for swapping the views and start the second half of the animation.
	 */
	private final class SwapViews implements Runnable {
		private final int mPosition;

		public SwapViews(int position) {
			mPosition = position;
		}

		public void run() {
			final float centerX = mContainer.getWidth() / 2.0f;
			final float centerY = mContainer.getHeight() / 2.0f;
			Rotate3dAnimation rotation;
			mFirstView = mContainer.getChildAt(0);
			mSecondView = mContainer.getChildAt(1);

			if (mPosition > -1) {
				mFirstView.setVisibility(View.GONE);
				mSecondView.setVisibility(View.VISIBLE);
				mSecondView.requestFocus();

				rotation = new Rotate3dAnimation(90, 0, centerX, centerY, depthZ, false);
			} else {
				mSecondView.setVisibility(View.GONE);
				mFirstView.setVisibility(View.VISIBLE);
				mFirstView.requestFocus();

				rotation = new Rotate3dAnimation(-90, 0, centerX, centerY, depthZ, false);
			}

			rotation.setDuration(DURATION);
			rotation.setFillAfter(true);
			rotation.setInterpolator(new DecelerateInterpolator());
			rotation.setAnimationListener(new DisplayPreviewsView());

			mContainer.startAnimation(rotation);

		}
	}

}
