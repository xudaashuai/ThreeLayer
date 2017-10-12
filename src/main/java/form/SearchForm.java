package form;

import bus.UserBus;
import vo.UserVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchForm {
    private JPanel rootPane;
    private JTextField nameInput;
    private JButton okButton;
    private JButton cancelButton;
    static JFrame frame;
    UserBus userBus = new UserBus();
    public SearchForm() {
        okButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                UserVO user = userBus.getUserEmailByName(nameInput.getText());
                if (user!=null){
                    JOptionPane.showConfirmDialog(null, user.getEmail(), "结果", JOptionPane.YES_OPTION);
                }else{
                    JOptionPane.showConfirmDialog(null, "没有找到", "结果", JOptionPane.YES_OPTION);

                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value != null && value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }

    public static void main(String[] args) {
        setUIFont(new javax.swing.plaf.FontUIResource("微软雅黑", Font.BOLD, 20));  //统一设置字体
        frame = new JFrame("SearchForm");
        frame.setContentPane(new SearchForm().rootPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
