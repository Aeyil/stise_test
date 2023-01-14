package project_16x16;

import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;

public interface IPApplet extends PConstants {


    public void background(int rgb);
    public void background(int rgb, float alpha);
    public void background(float gray);
    public void background(float gray, float alpha);
    public void background(float v1, float v2, float v3);
    public void background(float v1, float v2, float v3, float alpha);
    public void background(PImage image);

    public void image(PImage img, float a, float b);
    public void image(PImage img, float a, float b, float c, float d);
    public void image(PImage img, float a, float b, float c, float d, int u1, int v1, int u2, int v2);

    public PImage loadImage(String filename);
    public PImage loadImage(String filename, String extension);

    public PImage get();

    public void registerMethod(String methodName, Object target);
    public void unregisterMethod(String name, Object target);

    public void strokeWeight(float weight);
    public void noStroke();
    public void stroke(int rgb);
    public void stroke(int rgb, float alpha);
    public void stroke(float gray);
    public void stroke(float gray, float alpha);
    public void stroke(float v1, float v2, float v3);
    public void stroke(float v1, float v2, float v3, float alpha);

    public void fill(int rgb);
    public void fill(int rgb, float alpha);
    public void fill(float gray);
    public void fill(float gray, float alpha);
    public void fill(float v1, float v2, float v3);
    public void fill(float v1, float v2, float v3, float alpha);

    public void rect(float a, float b, float c, float d);
    public void rect(float a, float b, float c, float d, float r);
    public void rect(float a, float b, float c, float d, float tl, float tr, float br, float bl);

    public int color(int gray);
    public int color(float fgray);
    public int color(int gray, int alpha);
    public int color(int v1, int v2, int v3);
    public int color(int v1, int v2, int v3, int alpha);
    public int color(float v1, float v2, float v3);
    public int color(float v1, float v2, float v3, float alpha);

    public void textAlign(int alignX);
    public void textAlign(int alignX, int alignY);
    public void textSize(float size);
    public float textWidth(char c);
    public float textWidth(String str);
    public float textWidth(char[] chars, int start, int length);

    public void text(char c, float x, float y);
    public void text(char c, float x, float y, float z);
    public void text(String str, float x, float y);
    public void text(char[] chars, int start, int stop, float x, float y);
    public void text(String str, float x, float y, float z);
    public void text(char[] chars, int start, int stop, float x, float y, float z);
    public void text(String str, float x1, float y1, float x2, float y2);
    public void text(int num, float x, float y);
    public void text(int num, float x, float y, float z);
    public void text(float num, float x, float y);
    public void text(float num, float x, float y, float z);

    public void noFill();

    public void line(float x1, float y1, float x2, float y2);
    public void line(float x1, float y1, float z1, float x2, float y2, float z2);
    public void translate(float x, float y);
    public void translate(float x, float y, float z);
    public void rotate(float angle);
    public void rectMode(int mode);

    public float random(float high);
    public float random(float low, float high);

    public PGraphics createGraphics(int w, int h);
    public PGraphics createGraphics(int w, int h, String renderer);
    public PGraphics createGraphics(int w, int h, String renderer, String path);

    public String[] loadStrings(String filename);
    public int millis();

    public void scale(float s);
    public void scale(float x, float y);
    public void scale(float x, float y, float z);

    public void tint(int rgb);
    public void tint(int rgb, float alpha);
    public void tint(float gray);
    public void tint(float gray, float alpha);
    public void tint(float v1, float v2, float v3);
    public void tint(float v1, float v2, float v3, float alpha);
    public void noTint();

    public void ellipse(float a, float b, float c, float d);

    public void pushMatrix();
    public void popMatrix();

}
