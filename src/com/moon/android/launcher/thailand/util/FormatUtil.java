package com.moon.android.launcher.thailand.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
  
public class FormatUtil {  
    private static FormatUtil tools = new FormatUtil();  
  
    public static FormatUtil getInstance() {  
        if (tools == null) {  
            tools = new FormatUtil();  
            return tools;  
        }  
        return tools;  
    }  
  
    public InputStream Byte2InputStream(byte[] b) {  
        ByteArrayInputStream bais = new ByteArrayInputStream(b);  
        return bais;  
    }  
  
    public byte[] InputStream2Bytes(InputStream is) {  
        String str = "";  
        byte[] readByte = new byte[1024];  
        try {  
            while ((is.read(readByte, 0, 1024)) != -1) {  
                str += new String(readByte).trim();  
            }  
            return str.getBytes();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    public InputStream Bitmap2InputStream(Bitmap bm) {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);  
        InputStream is = new ByteArrayInputStream(baos.toByteArray());  
        return is;  
    }  
  
    public InputStream Bitmap2InputStream(Bitmap bm, int quality) {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        bm.compress(Bitmap.CompressFormat.PNG, quality, baos);  
        InputStream is = new ByteArrayInputStream(baos.toByteArray());  
        return is;  
    }  
  
    public Bitmap InputStream2Bitmap(InputStream is) {  
        return BitmapFactory.decodeStream(is);  
    }  
  
    public InputStream Drawable2InputStream(Drawable d) {  
        Bitmap bitmap = this.drawable2Bitmap(d);  
        return this.Bitmap2InputStream(bitmap);  
    }  
  
    public Drawable InputStream2Drawable(InputStream is) {  
        Bitmap bitmap = this.InputStream2Bitmap(is);  
        return this.bitmap2Drawable(bitmap);  
    }  
  
    public byte[] Drawable2Bytes(Drawable d) {  
        Bitmap bitmap = this.drawable2Bitmap(d);  
        return this.Bitmap2Bytes(bitmap);  
    }  
  
    public Drawable Bytes2Drawable(byte[] b) {  
        Bitmap bitmap = this.Bytes2Bitmap(b);  
        return this.bitmap2Drawable(bitmap);  
    }  
  
    public byte[] Bitmap2Bytes(Bitmap bm) {  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);  
        return baos.toByteArray();  
    }  
  
    public Bitmap Bytes2Bitmap(byte[] b) {  
        if (b.length != 0) {  
            return BitmapFactory.decodeByteArray(b, 0, b.length);  
        }  
        return null;  
    }  
  
    public Bitmap drawable2Bitmap(Drawable drawable) {  
        Bitmap bitmap = Bitmap  
                .createBitmap(  
                        drawable.getIntrinsicWidth(),  
                        drawable.getIntrinsicHeight(),  
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888  
                                : Bitmap.Config.RGB_565);  
        Canvas canvas = new Canvas(bitmap);  
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),  
                drawable.getIntrinsicHeight());  
        drawable.draw(canvas);  
        return bitmap;  
    }  
  
    public Drawable bitmap2Drawable(Bitmap bitmap) {  
        BitmapDrawable bd = new BitmapDrawable(bitmap);  
        Drawable d = (Drawable) bd;  
        return d;  
    }  
}  