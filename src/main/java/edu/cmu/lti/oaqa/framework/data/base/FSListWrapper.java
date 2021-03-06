package edu.cmu.lti.oaqa.framework.data.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.oaqa.model.OAQATop;

import edu.cmu.lti.oaqa.framework.BaseJCasHelper;

public abstract class FSListWrapper<T extends OAQATop> implements ContainerWrapper<T> {

  protected JCas jcas;

  protected FSList list;

  public FSListWrapper(JCas jcas) {
    this.jcas = jcas;
    list = new FSList(jcas);
  }

  @Override
  public void add(AnnotationWrapper<T> annotation) throws Exception {
    list = BaseJCasHelper.addToFSList(jcas, list, annotation.unwrap(jcas));
  }

  @Override
  public abstract void clear();

  @Override
  public abstract void complete();

  protected final <W extends AnnotationWrapper<T>> void setList(Collection<W> wrappers)
          throws Exception {
    clear();
    for (W wrapper : wrappers) {
      add(wrapper);
    }
    complete();
  }

  protected final <W extends AnnotationWrapper<T>> List<W> getList(Class<T> type,
          Class<W> classWrapper) throws AnalysisEngineProcessException {
    List<W> result = new ArrayList<W>();
    for (OAQATop top : BaseJCasHelper.<OAQATop> fsIterator(list)) {
      result.add(BaseAnnotationWrapper.wrap(top, type, classWrapper));
    }
    return result;
  }
}
