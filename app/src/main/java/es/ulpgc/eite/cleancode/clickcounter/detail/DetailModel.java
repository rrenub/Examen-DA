package es.ulpgc.eite.cleancode.clickcounter.detail;

import es.ulpgc.eite.cleancode.clickcounter.data.CounterData;

public class DetailModel implements DetailContract.Model {

  public static String TAG = DetailModel.class.getSimpleName();

  private CounterData counter;
  private int totalClicks;

  @Override
  public void onRestartScreen(CounterData counter, int totalClicks) {
    //Log.e(TAG, "onRestartScreen()");
    setCounter(counter);
    setTotalClicks(totalClicks);
  }

  @Override
  public void onDataFromNextScreen(CounterData data) {
    // Log.e(TAG, "onDataFromNextScreen()");
  }

  @Override
  public void onDataFromPreviousScreen(CounterData data) {

  }

  @Override
  public void addClick() {
    counter.value++;
    totalClicks++;
  }

  @Override
  public void setCounter(CounterData counter) {
    this.counter = counter;
  }

  @Override
  public void setTotalClicks(int totalClicks) {
    this.totalClicks = totalClicks;
  }

  public CounterData getCounter() {
    return counter;
  }

  public int getTotalClicks() {
    return totalClicks;
  }
}
