package edu.cmu.lti.oaqa.baseqa.gerpphase.core.pruner;

import java.util.Collection;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;

import com.google.common.collect.Lists;

import edu.cmu.lti.oaqa.baseqa.data.gerp.PruningDecisionWrapper;
import edu.cmu.lti.oaqa.baseqa.data.gerp.RankWrapper;

public abstract class AbstractIndependentPruner extends AbstractPruner {

  protected abstract PruningDecisionWrapper prune(Collection<? extends RankWrapper> rank);

  @Override
  public List<PruningDecisionWrapper> prune(List<? extends Collection<? extends RankWrapper>> ranks)
          throws AnalysisEngineProcessException {
    List<PruningDecisionWrapper> pruningDecisions = Lists.newArrayList();
    for (Collection<? extends RankWrapper> rank : ranks) {
      pruningDecisions.add(prune(rank));
    }
    return pruningDecisions;
  }
}