//package newair.com.marriagenetwork.push;
//
//import android.app.Notification;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.newair.mateng.R;
//import com.newair.mateng.activity.main.MainActivity;
//import com.newair.mateng.activity.main.other.ActiveActivity;
//import com.newair.mateng.activity.restaurant.EateryActivity;
//import com.newair.mateng.data.GlobalConstant;
//import com.newair.mateng.utils.L;
//import com.newair.mateng.utils.SPUtils;
//import com.newair.mateng.utils.T;
//import com.umeng.common.message.Log;
//import com.umeng.message.UTrack;
//import com.umeng.message.UmengBaseIntentService;
//import com.umeng.message.entity.UMessage;
//
//import org.android.agoo.client.BaseConstants;
//import org.json.JSONObject;
//
///**
// * Developer defined push intent service.
// * Remember to call {@link com.umeng.message.PushAgent#setPushIntentServiceClass(Class)}.
// *
// * @author lucas
// */
////完全自定义处理类
////参考文档的1.6.5
////http://dev.umeng.com/push/android/integration#1_6_5
//public class MyPushIntentService extends UmengBaseIntentService {
//    private static final String TAG = MyPushIntentService.class.getName();
//
//    @Override
//    protected void onMessage(Context context, Intent intent) {
//        // 需要调用父类的函数，否则无法统计到消息送达
//        super.onMessage(context, intent);
//        if((boolean)SPUtils.get(context, GlobalConstant.ISPUSH_SHARED,false))
//                return;
//        try {
//            //可以通过MESSAGE_BODY取得消息体
//            String message = intent.getStringExtra(BaseConstants.MESSAGE_BODY);
//            UMessage msg = new UMessage(new JSONObject(message));
//            T.show(context, message.toString(), Toast.LENGTH_LONG);
//            L.i(message);
//            L.i("message=" + message);    //消息体
//            L.i("custom=" + msg.custom);    //自定义消息的内容
//            L.i("title=" + msg.title);    //通知标题
//            L.i("text=" + msg.text);    //通知内容
//            JSONObject jsonObject = msg.getRaw().getJSONObject("extra");
//            int notice_type = jsonObject.getInt("notice_type");
//            int restaurant_id = jsonObject.getInt("restaurant_id");
//            int active_id = jsonObject.getInt("active_id");
//            // code  to handle message here
//            // ...
//            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            Notification n = new Notification();
//            n.icon = R.drawable.icon;
//            n.tickerText = msg.ticker;
//            n.defaults = Notification.DEFAULT_SOUND;
//            n.flags = Notification.FLAG_AUTO_CANCEL;
//            Intent in = null;
//            if (notice_type == 1) {
//                in = new Intent(context, MainActivity.class);
//            } else if (notice_type == 2) {
//                in = new Intent(context, ActiveActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("actice_id", active_id + "");
//                in.putExtras(bundle);
//            } else if (notice_type == 3) {
//                in = new Intent(context, EateryActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("restaurant_id", restaurant_id + "");
//                in.putExtras(bundle);
//            }
//            in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            PendingIntent pIntent = PendingIntent.getActivity(context, 0,
//                    in, PendingIntent.FLAG_UPDATE_CURRENT);
//            // 设置通知的标题和内容
//            n.setLatestEventInfo(context, msg.title,
//                    msg.text, pIntent);
//            nm.notify(0, n);
//            // 对完全自定义消息的处理方式，点击或者忽略
//            boolean isClickOrDismissed = true;
//            if (isClickOrDismissed) {
//                //完全自定义消息的点击统计
//                UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
//            } else {
//                //完全自定义消息的忽略统计
//                UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
//            }
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//        }
//    }
//}
