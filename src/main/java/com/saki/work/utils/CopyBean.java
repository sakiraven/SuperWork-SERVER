package com.saki.work.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author lzh
 * @date 2020/5/28 - 16:16
 * @Description
 */
public class CopyBean<T> {
    public List<T> copyList(Object sourceList, List<T> targetList, Class<T> toClass) {
        if ((!Objects.isNull(sourceList)) && (!Objects.isNull(targetList))) {
            List list1 = (List) sourceList;
            list1.forEach(item -> {
                try {
                    T data = toClass.newInstance();
                    BeanUtils.copyProperties(item, data);
                    targetList.add(data);
                } catch (InstantiationException e) {
                } catch (IllegalAccessException e) {
                }
            });
        }
        return targetList;
    }

    public T copyVO(Object sourceVO, T data, Class<T> toClass) {
        T t = null;
        try {
            t = toClass.newInstance();
            BeanUtils.copyProperties(sourceVO, t);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        data = t;
        return t;
    }
}
