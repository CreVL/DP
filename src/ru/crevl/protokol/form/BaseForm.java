package ru.crevl.protokol.form;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BaseForm extends JFrame {
    public BaseForm(int width, int height){
        super();
        setMinimumSize(new Dimension(width,height));
        setTitle("ДП");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(new Point((Toolkit.getDefaultToolkit().getScreenSize().width-width)/2,
                (Toolkit.getDefaultToolkit().getScreenSize().height-height)/2));


        try {
            setIconImage(ImageIO.read(BaseForm.class.getClassLoader().getResource("iconDp2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}