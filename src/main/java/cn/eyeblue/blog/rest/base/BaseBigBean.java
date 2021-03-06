package cn.eyeblue.blog.rest.base;

import cn.eyeblue.blog.config.AppContextManager;
import cn.eyeblue.blog.config.exception.UtilException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public abstract class BaseBigBean {


    //检出一个指定类型的实例。找不到抛异常。
    protected <T extends BaseEntity> T check(Class<T> clazz, String uuid) {
        return AppContextManager.check(clazz, uuid);
    }

    //找出一个指定类型的实例。找不到返回null
    protected <T extends BaseEntity> T find(Class<T> clazz, String uuid) {
        return AppContextManager.find(clazz, uuid);
    }

    //对page和pageSize进行验证
    protected PageRequest getPageRequest(int page, int pageSize, Sort sort) {

        if (page < 0 || pageSize < 1 || pageSize > 100) {
            throw new UtilException("Exceed the pager limitation.");
        }

        if (sort == null) {
            return PageRequest.of(page, pageSize);
        } else {
            return PageRequest.of(page, pageSize, sort);
        }

    }

    //and组装两个sort.
    protected Sort and(Sort sort1, Sort sort2) {

        if (sort1 == null) {
            return sort2;
        } else if (sort2 == null) {
            return sort1;
        } else {
            return sort1.and(sort2);
        }

    }


}
