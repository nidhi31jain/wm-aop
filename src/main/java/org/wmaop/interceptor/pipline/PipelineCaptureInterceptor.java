package org.wmaop.interceptor.pipline;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.wmaop.aop.interceptor.FlowPosition;
import org.wmaop.aop.interceptor.InterceptResult;
import org.wmaop.interceptor.BaseInterceptor;

import com.wm.data.IData;
import com.wm.util.coder.IDataXMLCoder;

public class PipelineCaptureInterceptor extends BaseInterceptor {

	private final String prefix;
	private final String suffix;
	private int fileCount;

	public PipelineCaptureInterceptor(String fileName) {
		super("PipelineCapture-"+fileName);
		int dotPos = fileName.lastIndexOf('.');
		if (dotPos == -1) {
			prefix = fileName;
			suffix = ".xml";
		} else {
			prefix = fileName.substring(0, dotPos);
			suffix = fileName.substring(dotPos);
		}
	}

	public InterceptResult intercept(FlowPosition flowPosition, IData idata) {
		invokeCount++;
		String fname = prefix + '-' + ++fileCount + suffix;
		try (OutputStream fos = getFileOutputStream(fname)) {
			new IDataXMLCoder().encode(fos, idata);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return InterceptResult.TRUE;
	}

	OutputStream getFileOutputStream(String fileName) throws FileNotFoundException {
		return new FileOutputStream(fileName);
	}
}
