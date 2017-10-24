package form;

import bus.UserBus;
import vo.UserVO;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SearchForm {
    private JPanel rootPane;
    private JTextField firstNameInput;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField idInput;
    private JTextField emailInput;
    private JTextField lastNameInput;
    static JFrame frame;

    public void setData(UserVO data) {
        firstNameInput.setText(data.getFirstName());
        lastNameInput.setText(data.getLastName());
        emailInput.setText(data.getEmail());
    }

    public void getData(UserVO data) {
        data.setFirstName(firstNameInput.getText());
        data.setLastName(lastNameInput.getText());
        data.setEmail(emailInput.getText());
    }

    public boolean isModified(UserVO data) {
        return (firstNameInput.getText() != null ? !firstNameInput.getText().equals(data.getFirstName()) : data.getFirstName() != null) || (lastNameInput.getText() != null ? !lastNameInput.getText().equals(data.getLastName()) : data.getLastName() != null) || (emailInput.getText() != null ? !emailInput.getText().equals(data.getEmail()) : data.getEmail() != null);
    }

    class UserListTableModel implements TableModel {
        List<UserVO> users;
        String[] colNames = new String[]{"id", "email", "firstname", "lastname"};

        public UserListTableModel(List<UserVO> users) {
            this.users = users;
        }

        /**
         * Returns the number of rows in the model. A
         * <code>JTable</code> uses this method to determine how many rows it
         * should display.  This method should be quick, as it
         * is called frequently during rendering.
         *
         * @return the number of rows in the model
         * @see #getColumnCount
         */
        public int getRowCount() {
            return users.size();
        }

        /**
         * Returns the number of columns in the model. A
         * <code>JTable</code> uses this method to determine how many columns it
         * should create and display by default.
         *
         * @return the number of columns in the model
         * @see #getRowCount
         */
        public int getColumnCount() {
            return 4;
        }

        /**
         * Returns the name of the column at <code>columnIndex</code>.  This is used
         * to initialize the table's column header name.  Note: this name does
         * not need to be unique; two columns in a table can have the same name.
         *
         * @param columnIndex the index of the column
         * @return the name of the column
         */
        public String getColumnName(int columnIndex) {
            return colNames[columnIndex];
        }

        /**
         * Returns the most specific superclass for all the cell values
         * in the column.  This is used by the <code>JTable</code> to set up a
         * default renderer and editor for the column.
         *
         * @param columnIndex the index of the column
         * @return the common ancestor class of the object values in the model.
         */
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        /**
         * Returns true if the cell at <code>rowIndex</code> and
         * <code>columnIndex</code>
         * is editable.  Otherwise, <code>setValueAt</code> on the cell will not
         * change the value of that cell.
         *
         * @param rowIndex    the row whose value to be queried
         * @param columnIndex the column whose value to be queried
         * @return true if the cell is editable
         * @see #setValueAt
         */
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex != 0;
        }

        /**
         * Returns the value for the cell at <code>columnIndex</code> and
         * <code>rowIndex</code>.
         *
         * @param rowIndex    the row whose value is to be queried
         * @param columnIndex the column whose value is to be queried
         * @return the value Object at the specified cell
         */
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return users.get(rowIndex).getId();
                case 1:
                    return users.get(rowIndex).getEmail();
                case 2:
                    return users.get(rowIndex).getFirstName();
                case 3:
                    return users.get(rowIndex).getLastName();
            }
            return null;
        }

        /**
         * Sets the value in the cell at <code>columnIndex</code> and
         * <code>rowIndex</code> to <code>aValue</code>.
         *
         * @param aValue      the new value
         * @param rowIndex    the row whose value is to be changed
         * @param columnIndex the column whose value is to be changed
         * @see #getValueAt
         * @see #isCellEditable
         */
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    users.get(rowIndex).setId((Integer) aValue);
                    break;
                case 1:
                    users.get(rowIndex).setEmail((String) aValue);
                    break;
                case 2:
                    users.get(rowIndex).setFirstName((String) aValue);
                    break;
                case 3:
                    users.get(rowIndex).setLastName((String) aValue);
                    break;
            }
        }

        /**
         * Adds a listener to the list that is notified each time a change
         * to the data model occurs.
         *
         * @param l the TableModelListener
         */
        public void addTableModelListener(TableModelListener l) {

        }

        /**
         * Removes a listener from the list that is notified each time a
         * change to the data model occurs.
         *
         * @param l the TableModelListener
         */
        public void removeTableModelListener(TableModelListener l) {

        }
    }

    UserBus userBus = new UserBus();

    public SearchForm() {
        okButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                UserVO userVO = new UserVO();
                getData(userVO);
                List<UserVO> users = userBus.getUserByUser(userVO);
                showResultForm(users);
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

    void showResultForm(List<UserVO> users) {
        final JFrame frame = new JFrame("ResultForm");
        final ResultForm resultForm = new ResultForm();
        resultForm.dataTable.setRowHeight(50);
        resultForm.submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserListTableModel tableModel = (UserListTableModel) resultForm.dataTable.getModel();
                for (UserVO u:tableModel.users) {
                    userBus.updateById(u);
                }
                JOptionPane.showMessageDialog(frame,"成功","ok",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        resultForm.label.setText("查询到" + users.size() + "个结果");
        resultForm.dataTable.setModel(new UserListTableModel(users));
        frame.setContentPane(resultForm.rootPanel);
        frame.pack();
        frame.setVisible(true);
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
