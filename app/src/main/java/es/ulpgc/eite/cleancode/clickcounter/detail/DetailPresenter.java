package es.ulpgc.eite.cleancode.clickcounter.detail;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.clickcounter.app.DetailToMasterState;
import es.ulpgc.eite.cleancode.clickcounter.app.MasterToDetailState;

public class DetailPresenter implements DetailContract.Presenter {

  public static String TAG = DetailPresenter.class.getSimpleName();

  private WeakReference<DetailContract.View> view;
  private DetailState state;
  private DetailContract.Model model;
  private DetailContract.Router router;

  public DetailPresenter(DetailState state) {
    this.state = state;
  }

  @Override
  public void onStart() {
    // Log.e(TAG, "onStart()");

    // initialize the state if is necessary
    if (state == null) {
      state = new DetailState();
    }
    // use passed state if is necessary
    MasterToDetailState savedState = router.getStateFromPreviousScreen();
    if (savedState != null) {

      // update the model if is necessary
      model.setCounter(savedState.counter);
      model.setTotalClicks(savedState.totalClicks);
    }
    state.counter = model.getCounter();
    state.totalClicks = model.getTotalClicks();
  }

  @Override
  public void onRestart() {
    // Log.e(TAG, "onRestart()");

    // update the model if is necessary
    model.onRestartScreen(state.counter, state.totalClicks);
  }

  @Override
  public void onResume() {
    // Log.e(TAG, "onResume()");

    // call the model and update the state
    //state.data = model.getStoredData();

    // update the view
    view.get().onDataUpdated(state);

  }

  @Override
  public void onBackPressed() {
    // Log.e(TAG, "onBackPressed()");
    DetailToMasterState passState = new DetailToMasterState();
    passState.counter = model.getCounter();
    passState.totalClicks = model.getTotalClicks();
    router.passStateToPreviousScreen(passState);
  }

  @Override
  public void onPause() {
    // Log.e(TAG, "onPause()");
  }

  @Override
  public void onDestroy() {
    // Log.e(TAG, "onDestroy()");
  }

  @Override
  public void onButtonPressed() {
    // Log.e(TAG, "onButtonPressed()");
    model.addClick();
    state.counter = model.getCounter();
    state.totalClicks = model.getTotalClicks();
    view.get().onDataUpdated(state);
  }

  @Override
  public void injectView(WeakReference<DetailContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(DetailContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(DetailContract.Router router) {
    this.router = router;
  }
}
