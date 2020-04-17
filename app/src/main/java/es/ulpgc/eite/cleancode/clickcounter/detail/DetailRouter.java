package es.ulpgc.eite.cleancode.clickcounter.detail;

import android.content.Context;
import android.content.Intent;

import es.ulpgc.eite.cleancode.clickcounter.app.AppMediator;
import es.ulpgc.eite.cleancode.clickcounter.app.DetailToMasterState;
import es.ulpgc.eite.cleancode.clickcounter.app.MasterToDetailState;

public class DetailRouter implements DetailContract.Router {

  public static String TAG = DetailRouter.class.getSimpleName();

  private AppMediator mediator;

  public DetailRouter(AppMediator mediator) {
    this.mediator = mediator;
  }

  @Override
  public void navigateToNextScreen() {
    Context context = mediator.getApplicationContext();
    Intent intent = new Intent(context, DetailActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }


  @Override
  public void passStateToPreviousScreen(DetailToMasterState state) {
    mediator.setPreviousDetailScreenState(state);
  }

  @Override
  public MasterToDetailState getStateFromPreviousScreen() {
    return mediator.getPreviousDetailScreenState();
  }

}
