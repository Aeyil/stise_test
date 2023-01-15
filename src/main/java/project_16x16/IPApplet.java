package project_16x16;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.data.JSONObject;

public interface IPApplet extends PConstants {


    void background(int rgb);
    void background(int rgb, float alpha);
    void background(float gray);
    void background(float gray, float alpha);
    void background(float v1, float v2, float v3);
    void background(float v1, float v2, float v3, float alpha);
    void background(PImage image);

    void image(PImage img, float a, float b);
    void image(PImage img, float a, float b, float c, float d);
    void image(PImage img, float a, float b, float c, float d, int u1, int v1, int u2, int v2);

    PImage loadImage(String filename);
    PImage loadImage(String filename, String extension);

    PImage get();

    void registerMethod(String methodName, Object target);
    void unregisterMethod(String name, Object target);

    void strokeWeight(float weight);
    void noStroke();
    void stroke(int rgb);
    void stroke(int rgb, float alpha);
    void stroke(float gray);
    void stroke(float gray, float alpha);
    void stroke(float v1, float v2, float v3);
    void stroke(float v1, float v2, float v3, float alpha);

    void fill(int rgb);
    void fill(int rgb, float alpha);
    void fill(float gray);
    void fill(float gray, float alpha);
    void fill(float v1, float v2, float v3);
    void fill(float v1, float v2, float v3, float alpha);

    void rect(float a, float b, float c, float d);
    void rect(float a, float b, float c, float d, float r);
    void rect(float a, float b, float c, float d, float tl, float tr, float br, float bl);

    int color(int gray);
    int color(float fgray);
    int color(int gray, int alpha);
    int color(int v1, int v2, int v3);
    int color(int v1, int v2, int v3, int alpha);
    int color(float v1, float v2, float v3);
    int color(float v1, float v2, float v3, float alpha);

    void textAlign(int alignX);
    void textAlign(int alignX, int alignY);
    void textSize(float size);
    float textWidth(char c);
    float textWidth(String str);
    float textWidth(char[] chars, int start, int length);

    void text(char c, float x, float y);
    void text(char c, float x, float y, float z);
    void text(String str, float x, float y);
    void text(char[] chars, int start, int stop, float x, float y);
    void text(String str, float x, float y, float z);
    void text(char[] chars, int start, int stop, float x, float y, float z);
    void text(String str, float x1, float y1, float x2, float y2);
    void text(int num, float x, float y);
    void text(int num, float x, float y, float z);
    void text(float num, float x, float y);
    void text(float num, float x, float y, float z);

    void noFill();

    void line(float x1, float y1, float x2, float y2);
    void line(float x1, float y1, float z1, float x2, float y2, float z2);
    void translate(float x, float y);
    void translate(float x, float y, float z);
    void rotate(float angle);
    void rectMode(int mode);

    float random(float high);
    float random(float low, float high);

    PGraphics createGraphics(int w, int h);
    PGraphics createGraphics(int w, int h, String renderer);
    PGraphics createGraphics(int w, int h, String renderer, String path);

    String[] loadStrings(String filename);
    int millis();

    void scale(float s);
    void scale(float x, float y);
    void scale(float x, float y, float z);

    void tint(int rgb);
    void tint(int rgb, float alpha);
    void tint(float gray);
    void tint(float gray, float alpha);
    void tint(float v1, float v2, float v3);
    void tint(float v1, float v2, float v3, float alpha);
    void noTint();

    void ellipse(float a, float b, float c, float d);

    void pushMatrix();
    void popMatrix();

    JSONObject loadJSONObject(String filename);
    PImage createImage(int w, int h, int format);
    void imageMode(int mode);
    void textLeading(float leading);

}
