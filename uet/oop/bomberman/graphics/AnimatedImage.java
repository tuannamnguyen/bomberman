package uet.oop.bomberman.graphics;

import java.util.List;

import javafx.scene.image.Image;

public class AnimatedImage {
    // assumes animation loops,
    // each image displays for equal time
    public List<Image> frames;
    public double duration;

    public AnimatedImage(List<Image> frames, double duration) {
        this.frames = frames;
        this.duration = duration;
    }

    public Image getFrame(double time) {
        int index = (int) ((time % (frames.size() * duration)) / duration);
        return frames.get(index);
    }

    public List<Image> getFrames() {
        return frames;
    }

    public void setFrames(List<Image> frames) {
        this.frames = frames;
    }

    
}