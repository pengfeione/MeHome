package com.mehome.utils;

import java.util.ArrayList;
import java.util.List;

public class CollectionsUtils {
    /**
     * 将list等分
     *
     * @param list
     * @param pieceSize
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> cutPieces(List<T> list, int pieceSize) {
        List<List<T>> lists = new ArrayList<List<T>>();
        if (list != null && pieceSize > 0) {
            int listSize = list.size();
            if (listSize <= pieceSize) {
                lists.add(list);
                return lists;
            }
            int batchSize = listSize / pieceSize;
            int remain = listSize % pieceSize;
            for (int i = 0; i < batchSize; i++) {
                int fromIndex = i * pieceSize;
                int toIndex = fromIndex + pieceSize;
                lists.add(list.subList(fromIndex, toIndex));
            }
            if (remain > 0) {
                lists.add(list.subList(listSize - remain, listSize));
            }
        }
        return lists;
    }

    /**
     * list分页
     *
     * @param pageNo
     * @param pageSize
     * @param list
     * @return
     */
    public static <T> List<T> page(int pageNo, int pageSize, List<T> list) {
        List<T> result = new ArrayList<T>();
        if (list != null && list.size() > 0) {
            int allCount = list.size();
            int pageCount = (allCount + pageSize - 1) / pageSize;
            if (pageNo >= pageCount) {
                pageNo = pageCount;
            }
            int start = (pageNo - 1) * pageSize;
            int end = pageNo * pageSize;
            if (end >= allCount) {
                end = allCount;
            }
            for (int i = start; i < end; i++) {
                result.add(list.get(i));
            }
        }
        return (result != null && result.size() > 0) ? result : new ArrayList<T>();
    }
}
