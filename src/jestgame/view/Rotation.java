package jestgame.view;

import java.awt.Dimension;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Rotation {
    private double angle;
    public Dimension dim;
    private PropertyChangeSupport diffuser;

    public Rotation() {
        this.angle = 0;
        this.dim = new Dimension();
        this.diffuser = new PropertyChangeSupport(this);
    }

    public Rotation(double angle) {
        this.angle = Math.toRadians(angle);
        this.angle %= Math.toRadians(360);
        this.diffuser = new PropertyChangeSupport(this);
    }

    public void addListener(PropertyChangeListener pce) {
        this.diffuser.addPropertyChangeListener(pce);
    }

    public void angle(double angle) {
        this.angle = Math.toRadians(angle);
        this.angle %= 360;
        this.dim.width = (int)(Math.cos(this.angle) * this.dim.width - Math.sin(this.angle) * this.dim.height);
        this.dim.height = (int)(Math.sin(this.angle) * this.dim.width + Math.cos(this.angle) * this.dim.height);
        this.diffuser.firePropertyChange("update", this.angle, this.angle);
    }
    public void setDimension(int w, int h) {
        this.dim.width = w;
        this.dim.height = h;
        this.diffuser.firePropertyChange("update", this.angle, this.angle);
    }

    public double angle(double angle, boolean inRad) {
            if (inRad) {
                this.angle = angle;
            }
            else {
                this.angle = Math.toRadians(angle);
            }
        this.dim.width = (int)(Math.cos(this.angle) * this.dim.width - Math.sin(this.angle) * this.dim.height);
        this.dim.height = (int)(Math.sin(this.angle) * this.dim.width + Math.cos(this.angle) * this.dim.height);
        this.diffuser.firePropertyChange("update", this.angle, this.angle);
        return this.angle;
    }

    public double angle() {
        return this.angle;
    }

    public double rotateCounterClockwise(double angle) { 
        this.angle(angle + this.angle);
        return this.angle;
    }

    public double rotateClockwise(double angle) {
        this.angle(angle - this.angle);
        return this.angle;
    }

    public double flip() {
        this.rotateCounterClockwise(180);
        return this.angle;
    }
}
