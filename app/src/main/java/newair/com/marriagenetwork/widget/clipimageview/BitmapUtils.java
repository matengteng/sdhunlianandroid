package newair.com.marriagenetwork.widget.clipimageview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mateng on 15/10/14.
 * 裁剪图片处理类
 */
public class BitmapUtils {



    /**
     * 根据传入的宽和高，计算出合适的inSampleSize值
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final float heightRatio = (float) height / (float) reqHeight;
            final float widthRatio = (float) width / (float) reqWidth;
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = (int) Math.ceil(heightRatio < widthRatio ? heightRatio : widthRatio);
        }
        return inSampleSize;
    }


    /**
     * 从文件中加载图片并压缩成指定大小
     * 先通过BitmapFactory.decodeStream方法，创建出一个bitmap，
     * 再调用上述方法将其设为ImageView的 source。decodeStream最大的秘密在
     * 于其直接调用JNI>>nativeDecodeAsset()来完成decode，无需再使用java层的createBitmap，
     * 从而节省了java层的空间
     * @param pathName
     * @param reqWidth
     * @param reqHeight
     * @return
     * @throws FileNotFoundException
     */
    public static Bitmap decodeSampledBitmapFromFile(String pathName,
                                                     int reqWidth, int reqHeight){
        // 加载位图
        InputStream is = null;
        try {
            is = new FileInputStream(pathName);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        // 为位图设置100K的缓存
        options.inTempStorage = new byte[100 * 1024];
        // 设置位图颜色显示优化方式
        // ALPHA_8：每个像素占用1byte内存（8位）
        // ARGB_4444:每个像素占用2byte内存（16位）
        // ARGB_8888:每个像素占用4byte内存（32位）
        // RGB_565:每个像素占用2byte内存（16位）
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        // 设置图片可以被回收，创建Bitmap用于存储Pixel的内存空间在系统内存不足时可以被回收
        options.inPurgeable = true;
        // 设置解码位图的尺寸信息
        options.inInputShareable = true;
        return BitmapFactory.decodeStream(is, null, options);
    }

    /**
     * 旋转图片
     * @param angle
     * @param mBitmap
     * @return
     */
    public static Bitmap rotaingImageBitmap(int angle, Bitmap mBitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap b = Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
        if(mBitmap!=null&&!mBitmap.isRecycled()){
            mBitmap.recycle();
            mBitmap=null;
        }
        return b;
    }

    /**
     * 读取图片旋转角度
     * 三星手机会旋转图片
     * @param imagePath
     * @return
     */
    public static int readPictureDegree(String imagePath) {
        int imageDegree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(imagePath);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    imageDegree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    imageDegree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    imageDegree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageDegree;
    }

    /**
     * 返回按比例缩减后的bitmap
     * @param bmp
     * @param width
     * @param height
     * @return
     */
    public static Bitmap picZoom(Bitmap bmp, int width, int height) {
        int bmpWidth = bmp.getWidth();
        int bmpHeght = bmp.getHeight();
        //等比例自动缩放图片适应控件
        float f1=(float)bmpWidth/width;
        float f2=(float)bmpHeght/height;
        float scale=1f;
        if(f1>1||f2>1){
            //放大
            scale=f1<f2?f2:f1;
        }else if(f1<1||f2<1){
            //缩小
            scale=f1<f2?f1:f2;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scale,scale);
        return Bitmap.createBitmap(bmp, 0, 0, bmpWidth, bmpHeght, matrix, true);
    }


    /**
     * 保存图像到本地
     * @param file
     * @param bit
     * @param flag 是否压缩
     * @param size 压缩的目标大小(kb)
     */
    public static void savePhoto(File file,Bitmap bit,boolean flag,int size){
        try {
            FileOutputStream fo=new FileOutputStream(file);
            if(flag){
                fo.write(compressBitmap(bit, size));
            }else{
                fo.write(getBitmapByte(bit));
            }
            fo.flush();
            fo.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 将图片的 Bitmap 转换成 byte[]
     *
     * @param bitmap
     * @return
     */
    public static byte[] getBitmapByte(Bitmap bitmap) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static byte[] compressBitmap(Bitmap bitmap, float size) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 如果签名是png的话，则不管quality是多少，都不会进行质量的压缩
        int quality = 100;
        while (baos.toByteArray().length / 1024f > size) {
            baos.reset();// 重置baos即清空baos
            quality = quality - 1;// 每次都减少4
            if (quality <= 0) {
                break;
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
            System.out.println("------质量--------" + baos.toByteArray().length
                    / 1024f);
        }
        return baos.toByteArray();
    }





}
