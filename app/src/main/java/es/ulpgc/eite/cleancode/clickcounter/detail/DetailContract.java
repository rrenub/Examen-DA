package es.ulpgc.eite.cleancode.clickcounter.detail;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.cleancode.clickcounter.app.DetailToMasterState;
import es.ulpgc.eite.cleancode.clickcounter.app.MasterToDetailState;
import es.ulpgc.eite.cleancode.clickcounter.data.CounterData;

public interface DetailContract {

  interface View {
    void injectPresenter(Presenter presenter);

    void onDataUpdated(DetailViewModel viewModel);
  }

  interface Presenter {
    void injectView(WeakReference<View> view);

    void injectModel(Model model);

    void injectRouter(Router router);

    void onResume();

    void onStart();

    void onRestart();

    void onBackPressed();

    void onPause();

    void onDestroy();

    void onButtonPressed();
  }

  interface Model {
    void onRestartScreen(CounterData counter, int totalClicks);

    void setCounter(CounterData counter);

    void setTotalClicks(int totalClicks);
    
    CounterData getCounter();

    int getTotalClicks();

    void addClick();
  }

  interface Router {
    void navigateToNextScreen();

    MasterToDetailState getStateFromPreviousScreen();

    void passStateToPreviousScreen(DetailToMasterState state);
  }
}
