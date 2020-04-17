package es.ulpgc.eite.cleancode.clickcounter.app;

import android.app.Application;

import es.ulpgc.eite.cleancode.clickcounter.detail.DetailState;
import es.ulpgc.eite.cleancode.clickcounter.master.MasterState;

public class AppMediator extends Application {

  private MasterState masterState;
  private DetailState detailState;

  private DetailToMasterState detailToMasterState;
  private MasterToDetailState masterToDetailState;

  @Override
  public void onCreate() {
    super.onCreate();

    masterState = new MasterState();
    detailState = new DetailState();
  }

  public MasterState getMasterState() {
    return masterState;
  }


  public DetailState getDetailState() {
    return detailState;
  }

  public void setPreviousDetailScreenState(DetailToMasterState state) {
    detailToMasterState=state;
  }

  public MasterToDetailState getPreviousDetailScreenState() {
    return masterToDetailState;
  }

  public void setNextMasterScreenState(MasterToDetailState state) {
    masterToDetailState=state;
  }

  public DetailToMasterState getNextMasterScreenState() {
    return detailToMasterState;
  }
}
