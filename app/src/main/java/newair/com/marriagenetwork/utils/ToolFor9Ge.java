package newair.com.marriagenetwork.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;

public class ToolFor9Ge 
{
 	public static Bitmap zoomImg(Bitmap bm, int newWidth ,int newHeight)
 	{ 
  	   int width = bm.getWidth();
  	   int height = bm.getHeight();
  	   float scaleWidth = ((float) newWidth) / width;
  	   float scaleHeight = ((float) newHeight) / height;
  	   Matrix matrix = new Matrix();
  	   matrix.postScale(scaleWidth, scaleHeight);
  	   Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
  	   return newbm;
 	}
 	
 	public static boolean checkNetworkInfo(Context mContext) {
 	  	ConnectivityManager conMan = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
 	  	State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
 	  	State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
 	  	if (mobile == State.CONNECTED || mobile == State.CONNECTING)
 	  	   return true;
 	  	if (wifi == State.CONNECTED || wifi == State.CONNECTING)
 	  	   return true;
 	  	return false;
 	}
 	
 	public static String getFileName(String pathandname){
 		int start=pathandname.lastIndexOf("/");  
         int end=pathandname.lastIndexOf(".");  
         if(start!=-1 && end!=-1){  
             return pathandname.substring(start+1,end);    
         }else{  
             return null;  
         }             
 	}

 	
 	public static String getBase64FromPath(String path)
 	{
 		String base64="";
 		try
 		{
 			File file = new File(path);
 			byte[] buffer = new byte[(int) file.length() + 100];  
			@SuppressWarnings("resource")
			int length = new FileInputStream(file).read(buffer);
 	        base64 = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);
 		}
 		catch (IOException e) {
			e.printStackTrace();
		}
 		return base64;
 	}
 	
 	public static Bitmap getBitmapFromPath(String path, int w, int h) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
		BitmapFactory.decodeFile(path, opts);
		int width = opts.outWidth;
		int height = opts.outHeight;
		float scaleWidth = 0.f, scaleHeight = 0.f;
		if (width > w || height > h) {
			scaleWidth = ((float) width) / w;
			scaleHeight = ((float) height) / h;
		}
		opts.inJustDecodeBounds = false;
		float scale = Math.max(scaleWidth, scaleHeight);
		opts.inSampleSize = (int)scale;
		WeakReference<Bitmap> weak = new WeakReference<Bitmap>(BitmapFactory.decodeFile(path, opts));
		return Bitmap.createScaledBitmap(weak.get(), w, h, true);
	}
 	
 	public static String getBase64FromBitmap(Bitmap bitmap, int bitmapQuality)
 	{
 		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
 		bitmap.compress(CompressFormat.PNG, bitmapQuality, bStream);
 		byte[] bytes = bStream.toByteArray();
 		return Base64.encodeToString(bytes, Base64.DEFAULT);
 	}
 	
 	public static Bitmap getBitmapFromBase64(String string)
 	{
 		byte[] bitmapArray = null;
 		try {
 		bitmapArray = Base64.decode(string, Base64.DEFAULT);
 		} catch (Exception e) {
 		e.printStackTrace();
 		}
 		return BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
 	}
 	
 	public static String convertStreamToString(InputStream is) {
 		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder sb = new StringBuilder();
			String line = null;

			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "/n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return sb.toString();	
 	}	
 	
 	public static void changeFonts(ViewGroup root,String path, Activity act) {
 		Typeface tf = Typeface.createFromAsset(act.getAssets(), path);
        for (int i = 0; i < root.getChildCount(); i++) {  
            View v = root.getChildAt(i);
            if (v instanceof TextView) {
               ((TextView) v).setTypeface(tf);
            } else if (v instanceof Button) {
               ((Button) v).setTypeface(tf);
            } else if (v instanceof EditText) {
               ((EditText) v).setTypeface(tf);
            } else if (v instanceof ViewGroup) {
               changeFonts((ViewGroup) v, path,act);
            } 
        }  
     }
 	
  	public static void changeTextSize(ViewGroup root,int size, Activity act) {
         for (int i = 0; i < root.getChildCount(); i++) {  
             View v = root.getChildAt(i);
             if (v instanceof TextView) {
                ((TextView) v).setTextSize(size);
             } else if (v instanceof Button) {
            	((Button) v).setTextSize(size);
             } else if (v instanceof EditText) {
            	((EditText) v).setTextSize(size);
             } else if (v instanceof ViewGroup) {
                changeTextSize((ViewGroup) v,size,act);
             }  
         }  
      }
  	
	public static void changeWH(View v,int W,int H)
	{
		LayoutParams params = v.getLayoutParams();
	    params.width = W;
	    params.height = H;
	    v.setLayoutParams(params);
	}
	
	public static void changeH(View v,int H)
	{
		LayoutParams params = v.getLayoutParams();
	    params.height = H;
	    v.setLayoutParams(params);
	}
		

}