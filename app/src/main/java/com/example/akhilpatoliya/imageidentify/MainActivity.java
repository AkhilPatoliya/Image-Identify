package com.example.akhilpatoliya.imageidentify;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageview_1,imageview_2;
    TextView txtresult_1,txtresult_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageview_1 = (ImageView) findViewById(R.id.imageview_1);
        imageview_2 = (ImageView) findViewById(R.id.imageview_2);
        txtresult_1 = (TextView) findViewById(R.id.textview_result1);
        txtresult_2 = (TextView) findViewById(R.id.textview_result2);

        imageview_1.setImageBitmap(identifyimage(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        imageview_2.setImageBitmap(identifyimage2(BitmapFactory.decodeResource(getResources(), R.drawable.ic_download)));
    }

    public Bitmap grayScaleImage(Bitmap src) {
        // constant factors
        final double GS_RED = 0.299;
        final double GS_GREEN = 0.587;
        final double GS_BLUE = 0.114;

        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // pixel information
        int A, R, G, B;
        int pixel = 0;

        // get image size
        int width = src.getWidth();
        int height = src.getHeight();

        // scan through every single pixel
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // get one pixel color
                pixel = src.getPixel(x, y);
                // retrieve color of all channels
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);

                // take conversion up to one single value
                R = G = B = (int) (GS_RED * R + GS_GREEN * G + GS_BLUE * B);

                // set new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));


            }
        }
        // return final image
        return bmOut;
    }

    public Bitmap doColorFilter(Bitmap originalImage, double red, double green, double blue) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        Bitmap bmOut = Bitmap.createBitmap(width, height, originalImage.getConfig());
        int A, R, G, B;
        int pixel;
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                pixel = originalImage.getPixel(x, y);
                A = Color.alpha(pixel);
                R = (int) (Color.red(pixel) * red);
                G = (int) (Color.green(pixel) * green);
                B = (int) (Color.blue(pixel) * blue);
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        return bmOut;
    }
    public Bitmap identifyimage(Bitmap src) {
        int width = src.getWidth();
        int height = src.getHeight();

        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
        double red = 0, green = 0, blue = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = src.getPixel(i, j);
                int redValue = Color.red(pixel);
                int blueValue = Color.blue(pixel);
                int greenValue = Color.green(pixel);
                int A,R,G,B;
                pixel = src.getPixel(i, j);
                A = Color.alpha(pixel);
                R = (int) (Color.red(pixel) + red);
                G = (int) (Color.green(pixel) + green);
                B = (int) (Color.blue(pixel) + blue);

                int R1 = (0x00ff0000 & (Color.red(pixel))) >> 16;
                int G1 = (0x0000ff00 & (Color.red(pixel))) >> 8;
                int B1 = (0x000000ff & (Color.red(pixel)));

                // take conversion up to one single value
                //     R = G = B = (int) (GS_RED * R + GS_GREEN * G + GS_BLUE * B);

                bmOut.setPixel(i, j, Color.argb(A, R, G, B));
                if (bmOut.getPixel(i, j) == Color.argb(A, R, G, B) && bmOut.getPixel(i, j) == Color.argb(A, R1, G1, B1)) {
                    txtresult_1.setText("Result :- colored");
                } else {
                    txtresult_1.setText("Result :- not colored");
                }
            }
        }
        return bmOut;
    }


    public Bitmap identifyimage2(Bitmap src1) {
        int width = src1.getWidth();
        int height = src1.getHeight();

        Bitmap bmOut = Bitmap.createBitmap(width, height, src1.getConfig());
        double red = 0, green = 0, blue = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int pixel = src1.getPixel(i, j);

                int redValue = Color.red(pixel);
                int blueValue = Color.blue(pixel);
                int greenValue = Color.green(pixel);

                int A;
                int R;
                int G;
                int B;
                pixel = src1.getPixel(i, j);
                A = Color.alpha(pixel);
                R = (int) (Color.red(pixel) + red);
                G = (int) (Color.green(pixel) + green);
                B = (int) (Color.blue(pixel) + blue);


                int R1 = (0x00ff0000 & (Color.red(pixel))) >> 16;
                int G1 = (0x0000ff00 & (Color.red(pixel))) >> 8;
                int B1 = (0x000000ff & (Color.red(pixel)));

                bmOut.setPixel(i, j, Color.argb(A, R, G, B));
                if (bmOut.getPixel(i, j) == Color.argb(A, R, G, B) && bmOut.getPixel(i, j) == Color.argb(A, R1, G1, B1)) {
                    txtresult_2.setText("Result :- colored");

                } else {
                    txtresult_2.setText("Result :- not colored");
                }
            }
        }
        return bmOut;
    }

}
