package Module_8.Homework8.Geometry;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class MyRectangle extends Rectangle {
    private Course course;
    private static ArrayList<MyRectangle> rectangles = new ArrayList<>();

    public MyRectangle(int minWidth, int maxWidth, int minHeight, int maxHeight){
        super((minWidth+Math.random()*(maxWidth-minWidth+1)),(minHeight+Math.random()*(maxHeight-minHeight+1)),Color.color(Math.random(),Math.random(),Math.random()));
        this.course=randomCourse();
        this.setTranslateX(100+Math.random()*600);
        this.setTranslateY(100+Math.random()*350);
        rectangles.add(this);
    }

    private Course randomCourse() {
        Course randomCourse=Course.LeftDown;
        Random random = new Random();
        int rnd = random.nextInt(4);
        if (rnd ==0) randomCourse=Course.LeftUp;
        if (rnd ==1) randomCourse=Course.RightDown;
        if (rnd ==2) randomCourse=Course.RightUp;

        return randomCourse;
    }


    public void move(){
        isBorder();
        if (Main.isCollide){
            collide();
        }
        if (course==Course.RightDown){
            this.setTranslateX(this.getTranslateX()+1);
            this.setTranslateY(this.getTranslateY()+1);
        }
        if (course==Course.LeftDown){
            this.setTranslateX(this.getTranslateX()-1);
            this.setTranslateY(this.getTranslateY()+1);
        }
        if (course==Course.LeftUp){
            this.setTranslateX(this.getTranslateX()-1);
            this.setTranslateY(this.getTranslateY()-1);
        }
        if (course==Course.RightUp){
            this.setTranslateX(this.getTranslateX()+1);
            this.setTranslateY(this.getTranslateY()-1);
        }

    }

    private void collide() {
        for(int i =0;i<rectangles.size();i++){
            if (this!=rectangles.get(i)){
               if (this.getBoundsInParent().intersects(rectangles.get(i).getBoundsInParent())) {
                   Bounds bounds1 = this.getBoundsInParent();
                   Bounds bounds2 = rectangles.get(i).getBoundsInParent();
                   if (this.getCourse()==Course.RightUp) {
                       if ((bounds1.getMaxX()-bounds2.getMinX())<(bounds2.getMaxY()-bounds1.getMinY())){
                           this.setCourse(Course.LeftUp);
                           if (rectangles.get(i).getCourse()==Course.LeftUp){
                               rectangles.get(i).setCourse(Course.RightUp);
                           } else {
                               rectangles.get(i).setCourse(Course.RightDown);
                           }
                       } else {
                           this.setCourse(Course.RightDown);
                           if (rectangles.get(i).getCourse()==Course.LeftDown){
                               rectangles.get(i).setCourse(Course.LeftUp);
                           } else {
                               rectangles.get(i).setCourse(Course.RightUp);
                           }
                       }
                   } else if(this.getCourse()==Course.LeftUp){
                       if ((bounds2.getMaxX()-bounds1.getMinX())<(bounds2.getMaxY()-bounds1.getMinY())){
                           this.setCourse(Course.RightUp);
                           if (rectangles.get(i).getCourse()==Course.RightUp){
                               rectangles.get(i).setCourse(Course.LeftUp);
                           } else {
                               rectangles.get(i).setCourse(Course.LeftDown);
                           }
                       } else {
                           this.setCourse(Course.LeftDown);
                           if (rectangles.get(i).getCourse()==Course.LeftDown){
                               rectangles.get(i).setCourse(Course.LeftUp);
                           } else {
                               rectangles.get(i).setCourse(Course.RightUp);
                           }
                       }
                   } else if (this.getCourse()==Course.RightDown) {
                       if ((bounds1.getMaxX()-bounds2.getMinX())<(bounds1.getMaxY()-bounds2.getMinY())){
                           this.setCourse(Course.LeftDown);
                           if (rectangles.get(i).getCourse()==Course.LeftUp){
                               rectangles.get(i).setCourse(Course.RightUp);
                           } else {
                               rectangles.get(i).setCourse(Course.RightDown);
                           }
                       } else {
                           this.setCourse(Course.RightUp);
                           if (rectangles.get(i).getCourse()==Course.LeftUp){
                               rectangles.get(i).setCourse(Course.LeftDown);
                           } else {
                               rectangles.get(i).setCourse(Course.RightDown);
                           }
                       }
                   }else if (this.getCourse()==Course.LeftDown) {
                       if ((bounds2.getMaxX()-bounds1.getMinX())<(bounds1.getMaxY()-bounds2.getMinY())){
                           this.setCourse(Course.RightDown);
                           if (rectangles.get(i).getCourse()==Course.RightUp){
                               rectangles.get(i).setCourse(Course.LeftUp);
                           } else {
                               rectangles.get(i).setCourse(Course.LeftDown);
                           }
                       } else {
                           this.setCourse(Course.LeftUp);
                           if (rectangles.get(i).getCourse()==Course.LeftUp){
                               rectangles.get(i).setCourse(Course.LeftDown);
                           } else {
                               rectangles.get(i).setCourse(Course.RightDown);
                           }
                       }
                   }


               }
            }
        }
    }

    private void isBorder() {
        if (this.getTranslateX()<=0){
            if (course==Course.LeftUp) setCourse(Course.RightUp);
            if (course==Course.LeftDown) setCourse(Course.RightDown);

        }
        if ((this.getTranslateX()+this.getWidth())>=(Main.WINDOW_WIDTH)-14){
            if (course==Course.RightUp) setCourse(Course.LeftUp);
            if (course==Course.RightDown) setCourse(Course.LeftDown);

        }
        if (this.getTranslateY()<=0){
            if (course==Course.RightUp) setCourse(Course.RightDown);
            if (course==Course.LeftUp) setCourse(Course.LeftDown);


        }
        if ((this.getTranslateY()+this.getHeight())>=Main.WINDOW_HEIGHT-115){
            if (course==Course.RightDown) setCourse(Course.RightUp);
            if (course==Course.LeftDown) setCourse(Course.LeftUp);

        }
    }


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
