package Swing;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

public class PanelSlide extends javax.swing.JPanel {

    private int animate = 1;
    private Timer timer;
    private Component com1;
    private Component com2;
    private Component com3;
    private Component com4;
    private Component com5;
    private List<Component> list = new ArrayList<>();
    private Component currentComponent;
    private Component targetComponent;
    private boolean animateRight;

    public PanelSlide() {
        initComponents();
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                animate();
            }
        });
    }

    public void setAnimate(int animate) {
        this.animate = animate;
    }

    public void init(Component main, Component c2, Component c3, Component c4, Component c5) {
        // Add all components to list
        list.add(main);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        list.add(c5);

        // Assign components to fields
        com1 = main;
        com2 = c2;
        com3 = c3;
        com4 = c4;
        com5 = c5;

        // Set up each component in the panel
        for (Component c : list) {
            c.setSize(getSize());
            c.setVisible(false);
            this.add(c);
        }

        // Show the main component initially
        com1.setVisible(true);
        com1.setLocation(0, 0);
        currentComponent = com1;
    }

    public void show(Component target) {
        if (!timer.isRunning() && target != currentComponent) {
            targetComponent = target;
            animateRight = list.indexOf(target) < list.indexOf(currentComponent);
            targetComponent.setVisible(true);

            // Set initial location based on direction
            if (animateRight) {
                targetComponent.setLocation(-targetComponent.getWidth(), 0);
            } else {
                targetComponent.setLocation(getWidth(), 0);
            }
            timer.start();
        }
    }

    private void animate() {
        if (animateRight) {
            if (targetComponent.getLocation().x < 0) {
                targetComponent.setLocation(targetComponent.getLocation().x + animate, 0);
                currentComponent.setLocation(currentComponent.getLocation().x + animate, 0);
            } else {
                completeAnimation();
            }
        } else {
            if (targetComponent.getLocation().x > 0) {
                targetComponent.setLocation(targetComponent.getLocation().x - animate, 0);
                currentComponent.setLocation(currentComponent.getLocation().x - animate, 0);
            } else {
                completeAnimation();
            }
        }
    }

    private void completeAnimation() {
        targetComponent.setLocation(0, 0);
        timer.stop();
        currentComponent.setVisible(false);
        currentComponent = targetComponent;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 479, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
