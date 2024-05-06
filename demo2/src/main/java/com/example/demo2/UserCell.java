package com.example.demo2;

public class UserCell extends org.controlsfx.control.GridCell<User> {
    @Override
    protected void updateItem(User item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            setText(item.getName());
        }
    }
}
