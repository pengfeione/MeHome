package com.mehome.requestDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */
public class BatchUserRequestDTO {
    private List<Integer> ids = new ArrayList<Integer>();

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public void addId(Integer id) {
        if (!ids.contains(id))
            ids.add(id);
    }

    public int getSize() {
        return ids.size();
    }
}
