package mysmax.com.retrofitapplication.model;

import java.util.ArrayList;

public class ExpandInfoList {

    private Group group;
    private ArrayList<Child> childrenList = new ArrayList<>();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public ArrayList<Child> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(ArrayList<Child> childrenList) {
        this.childrenList = childrenList;
    }
}
