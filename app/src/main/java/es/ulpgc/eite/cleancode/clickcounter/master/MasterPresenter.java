package es.ulpgc.eite.cleancode.clickcounter.master;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.clickcounter.app.DetailToMasterState;
import es.ulpgc.eite.cleancode.clickcounter.app.MasterToDetailState;
import es.ulpgc.eite.cleancode.clickcounter.data.CounterData;

public class MasterPresenter implements MasterContract.Presenter {

  public static String TAG = MasterPresenter.class.getSimpleName();

  private WeakReference<MasterContract.View> view;
  private MasterState state;
  private MasterContract.Model model;
  private MasterContract.Router router;

  public MasterPresenter(MasterState state) {
    this.state = state;
  }

  @Override
  public void onStart() {
    // Log.e(TAG, "onStart()");

    // initialize the state if is necessary
    if (state == null) {
      state = new MasterState();
    }
  }

  @Override
  public void selectCounterData(CounterData data) {
    MasterToDetailState passState = new MasterToDetailState();
    passState.counter = data;
    passState.totalClicks = model.getTotalClicks();
    router.passStateToNextScreen(passState);
    router.navigateToNextScreen();
  }

  @Override
  public void onRestart() {
    // Log.e(TAG, "onRestart()");

    // update the model if is necessary
    model.onRestartScreen(state.datasource, state.totalClicks);
  }


  @Override
  public void onResume() {
    // Log.e(TAG, "onResume()");

    // use passed state if is necessary
    DetailToMasterState savedState = router.getStateFromNextScreen();
    if (savedState != null) {
      // update the model if is necessary
      model.updateCounterData(savedState.counter);
      model.addClicksToTotal(savedState.totalClicks);
    }

    // call the model and update the state
    state.datasource = model.getStoredData();
    state.totalClicks = model.getTotalClicks();

    // update the view
    view.get().onDataUpdated(state);
  }

  @Override
  public void onBackPressed() {
    //no hace nada
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
    model.addNewCounter();
    view.get().onDataUpdated(state);
  }

  @Override
  public void injectView(WeakReference<MasterContract.View> view) {
    this.view = view;
  }

  @Override
  public void injectModel(MasterContract.Model model) {
    this.model = model;
  }

  @Override
  public void injectRouter(MasterContract.Router router) {
    this.router = router;
  }
}
