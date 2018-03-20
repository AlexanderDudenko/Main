package com.dudenko.study.concurrency.theme1.rectangular_area.src.main.java.com.netcracker;

import java.awt.*;

/**
 * Rectangular area in a coordinate space,
 * specified by a top left point, its width, height and name.
 */
public class RectangularAreaThreadSafe {
    private String name;
    private Point topLeft;
    private int width;
    private int height;

    /*
    1ый способ - это при сете клонировать объект
    2ой способ - синхронайз?
     */

    public RectangularAreaThreadSafe(String s, Point p, int w, int h) {
        name = s;
        topLeft = p;
        width = w;
        height = h;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Point getLocation() {
        return topLeft;
    }

    public String getName() {
        return name;
    }

    public void resize(int factor) {
        width *= factor;
        height *= factor;
    }

    public void translate(Point p) {
        topLeft.x += p.x;
        topLeft.y += p.y;
    }

    public void rename(String s) {
        name = s;
    }
}