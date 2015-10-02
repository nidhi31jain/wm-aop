package org.wmaop.aop;

import org.wmaop.aop.pipeline.FlowPosition;

import com.wm.data.IData;

public interface PointCut {

	boolean isApplicable(FlowPosition pipelinePosition, IData idata);

	InterceptPoint getInterceptPoint();
}