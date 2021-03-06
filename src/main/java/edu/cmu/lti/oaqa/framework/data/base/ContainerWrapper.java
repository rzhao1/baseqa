package edu.cmu.lti.oaqa.framework.data.base;

import org.oaqa.model.OAQATop;

public interface ContainerWrapper<T extends OAQATop> {

  void add(AnnotationWrapper<T> annotation) throws Exception;

  void clear();

  void complete();

}
