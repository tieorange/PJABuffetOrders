package tieorange.com.pjabuffetorders.pojo.api;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.google.firebase.database.Exclude;
import java.util.ArrayList;
import java.util.List;
import org.parceler.Parcel;
import tieorange.com.pjabuffetorders.R;
import tieorange.com.pjabuffetorders.ordersListLib.Product;

/**
 * Created by tieorange on 03/11/2016.
 */
@Parcel public class Order extends BaseObservable {
  public static final String ORDERED_ORDERS_START_WITH = "3";
  public static final String ORDERED_ORDERS_ENDS_WITH = ORDERED_ORDERS_START_WITH + "\\uf8ff";
  public static final String FINISHED_ORDERS_START_WITH = "2";

  public static final String STATE_ORDERED = "39";
  public static final String STATE_ACCEPTED = "38";
  public static final String STATE_READY = "29";
  public static final String STATE_REJECTED = "20";

  private List<Product> products = new ArrayList<>();
  private String clientName;
  private String status;
  @Exclude private String key;

  @Exclude private int position;

  @Exclude private int textColor;

  public Order() {
  }

  @Exclude public String getStatusString() {
    String statusNow = "NO STATE";
    if (getStatus().equals(STATE_ACCEPTED)) {
      statusNow = "Accepted";
    } else if (getStatus().equals(STATE_ORDERED)) {
      statusNow = "Ordered";
    } else if (getStatus().equals(STATE_READY)) {
      statusNow = "Ready";
    } else if (getStatus().equals(STATE_REJECTED)) {
      statusNow = "Rejected";
    }
    return statusNow;
  }

  @Exclude public void getExperiment(IStatesSwitch iStatesSwitch) {
    String statusNow = "NO STATE";
    if (getStatus().equals(STATE_ACCEPTED)) {
      iStatesSwitch.accepted();
    } else if (getStatus().equals(STATE_ORDERED)) {
      iStatesSwitch.ordered();
    } else if (getStatus().equals(STATE_READY)) {
      iStatesSwitch.ready();
    } else {
      //        if (getStatus() == STATE_REJECTED) {
      iStatesSwitch.rejected();
    }
    //        return statusNow;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  @Bindable public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
    notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.products);
  }

  @Bindable public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
    notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.clientName);
  }

  @Bindable public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
    notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.status);
  }

  @Bindable public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
    notifyPropertyChanged(com.android.databinding.library.baseAdapters.BR.position);
  }

  @Bindable public int getTextColor() {
    textColor = R.color.material_color_green_400;

    getExperiment(new IStatesSwitch() {
      @Override public void ordered() {
        textColor = R.color.material_color_yellow_800;
      }

      @Override public void accepted() {
        textColor = R.color.material_color_green_500;
      }

      @Override public void ready() {
        textColor = R.color.material_color_green_800;
      }

      @Override public void rejected() {
        textColor = R.color.material_color_red_500;
      }
    });
    return textColor;
  }

  interface IStatesSwitch {
    void ordered();

    void accepted();

    void ready();

    void rejected();
  }
}
